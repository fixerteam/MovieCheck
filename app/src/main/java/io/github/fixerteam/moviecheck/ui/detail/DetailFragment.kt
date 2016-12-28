package io.github.fixerteam.moviecheck.ui.detail

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import io.github.fixerteam.moviecheck.R
import io.github.fixerteam.moviecheck.domain.pojo.Video
import io.github.fixerteam.moviecheck.domain.pojo.Movie
import io.github.fixerteam.moviecheck.ui.base.mvp.BaseFragment
import io.github.fixerteam.moviecheck.ui.base.mvp.BasePresenter
import io.github.fixerteam.moviecheck.ui.main.MainComponent
import io.github.fixerteam.moviecheck.util.loadUrl
import org.jetbrains.anko.*
import javax.inject.Inject

class DetailFragment : BaseFragment(), DetailContract.View<Movie> {

  override fun setTrailer(trailer: Video) {
    coverContainer.tag = trailer
    //    coverContainer.setOnClickListener({ view -> mHelper.playVideo(view.tag as Video) })
  }

  override fun setVisibility(hasTrailer: Boolean, hasVideos: Boolean) {
//    showShareMenuItemDeferred(hasTrailer != null)
    coverContainer.isClickable = hasTrailer
    posterPlayImage.visibility = if (hasTrailer) android.view.View.VISIBLE else android.view.View.GONE
    videosGroup.visibility = if (hasVideos) android.view.View.VISIBLE else android.view.View.GONE
  }

  override fun addVideo(video: Video) {
    val inflater = LayoutInflater.from(activity)
    val videoView = inflater.inflate(R.layout.item_video, videosGroup, false)
    val videoNameView = videoView.findViewById(R.id.video_name) as TextView

    videoNameView.text = video.site + ": " + video.name
    videoView.tag = video
    //    videoView.setOnClickListener({ v -> mHelper.playVideo(v.getTag() as Video) })

    videosGroup.addView(videoView)
  }

  companion object {
    val MOVIE_ID = "movie_id"
    fun newInstance(productId: Int) =
        DetailFragment().apply { arguments = bundleOf(MOVIE_ID to productId) }
  }

  @Inject lateinit var presenter: DetailPresenter

  private lateinit var moviePoster: ImageView
  private lateinit var movieCover: ImageView
  private lateinit var posterPlayImage: ImageView
  private lateinit var movieTitle: TextView
  private lateinit var movieReleaseDate: TextView
  private lateinit var movieAverageRating: TextView
  private lateinit var movieOverview: TextView
  private lateinit var favoriteButton: ImageButton
  private lateinit var videosGroup: ViewGroup
  private lateinit var coverContainer: FrameLayout

  override fun showDetail(movie: Movie) {
    moviePoster.loadUrl(movie.posterPath, R.color.movie_cover_placeholder)
    movieCover.loadUrl(movie.backdropPath, R.color.movie_cover_placeholder)
    movieReleaseDate.text = movie.releaseDate
    movieTitle.text = movie.title
    movieAverageRating.text = getString(R.string.movie_details_rating, movie.voteAverage.toString())
    movieOverview.text = movie.overview
    favoriteButton.isSelected = movie.favored
  }

  override fun getPresenter(): BasePresenter<Movie, DetailContract.View<Movie>> = presenter

  override fun isReady(): Boolean = isAdded

  override fun showLoading() {
    //todo implement showLoading function!
    //    throw UnsupportedOperationException("not implemented")
  }

  override fun showEmpty(message: String) {
    //todo implement showEmpty function!
    //    throw UnsupportedOperationException("not implemented")
  }

  override fun showContent(content: List<Movie>) {
    //todo implement showContent function!
    //    throw UnsupportedOperationException("not implemented")
  }

  override fun showError(message: String) {
    //todo implement showError function!
    //    throw UnsupportedOperationException("not implemented")
  }

  override fun hideLoading() {
    //todo implement hideLoading function!
    //    throw UnsupportedOperationException("not implemented")
  }

  override fun getLayout(): View = DetailFragmentUi().createView(AnkoContext.create(context, this))

  override fun injectDependencies() {
    activityComponent<MainComponent>()
        ?.plusDetailMoviesSubComponent(DetailModule())
        ?.inject(this)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    moviePoster = view.find<ImageView>(R.id.movie_poster)
    movieCover = view.find<ImageView>(R.id.movie_cover)
    movieTitle = view.find<TextView>(R.id.movie_title)
    movieReleaseDate = view.find<TextView>(R.id.movie_release_date)
    movieAverageRating = view.find<TextView>(R.id.movie_average_rating)
    movieOverview = view.find<TextView>(R.id.movie_overview)
    favoriteButton = view.find<ImageButton>(R.id.movie_favorite_button)
    videosGroup = view.find<ViewGroup>(R.id.movie_videos_container)
    coverContainer = view.find<FrameLayout>(R.id.movie_cover_container)
    posterPlayImage = view.find<ImageView>(R.id.movie_poster_play)

    presenter.attachView(this)
    presenter.onStart()
    presenter.showDetail(arguments.getInt(MOVIE_ID))
  }

  /**
   * Remove all existing videos (everything but first two children)
   */
  override fun removeVideos() {
    for (i in videosGroup.getChildCount() - 1 downTo 2) {
      videosGroup.removeViewAt(i)
    }
  }

  class DetailFragmentUi : AnkoComponent<Fragment> {
    override fun createView(ui: AnkoContext<Fragment>) = with(ui) {
      include<View>(R.layout.detail_layout)
    }
  }
}