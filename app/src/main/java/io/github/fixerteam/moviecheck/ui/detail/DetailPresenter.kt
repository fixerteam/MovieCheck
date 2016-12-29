package io.github.fixerteam.moviecheck.ui.detail

import android.os.Bundle
import io.github.fixerteam.moviecheck.domain.MovieInteractor
import io.github.fixerteam.moviecheck.domain.pojo.Movie
import io.github.fixerteam.moviecheck.domain.pojo.Video
import io.github.fixerteam.moviecheck.ui.base.mvp.BasePresenter
import io.github.fixerteam.moviecheck.ui.detail.DetailContract.Presenter
import io.github.fixerteam.moviecheck.ui.detail.DetailContract.View
import java.util.*

class DetailPresenter(private val interactor: MovieInteractor) : BasePresenter<Movie, View<Movie>>(), Presenter {

  private val viewState = ViewState()

  private var videos: List<Video>? = null
  private var movie: Movie? = null
  private var trailer: Video? = null
  fun onSaveInstanceState(outState: Bundle?) {
    videos?.apply { outState?.putParcelableArrayList("VIDEOS_LIST_EXTRA", ArrayList(videos)) }
    movie?.apply { outState?.putParcelable("MOVIE_EXTRA", this) }
    trailer?.apply { outState?.putParcelable("TRAILER_EXTRA", this) }
  }

  fun onRestoreSaveInstanceState(savedInstanceState: Bundle) {
    videos = savedInstanceState.getParcelableArrayList<Video>("VIDEOS_LIST_EXTRA")
    movie = savedInstanceState.getParcelable("MOVIE_EXTRA")
    trailer = savedInstanceState.getParcelable("TRAILER_EXTRA")
    doIfViewReady {
      movie?.apply { showDetail(this) }
      onVideosLoaded(videos)
    }
  }

  override fun playVideo(video: Video) = doIfViewReady {
    playVideo(video)
  }

  override fun showDetail(movieId: Int) {
    if (! viewState.isLoading && ! viewState.isLoadOnce) {
      viewState.isLoading = true
      addSubscription(interactor.getMovie(movieId)
          .doOnNext { loadVideos(movieId) }
          .subscribe({
            doIfViewReady {
              movie = it
              showDetail(it)
            }
          }, { onError(it) }))
    } else {
      restoreState()
    }
  }

  override fun onStart() {
    if (! viewState.isLoadOnce || viewState.isLoading) {
      doIfViewReady { showLoading() }
    }
  }

  private fun restoreState() = doIfViewReady {
    movie?.apply { showDetail(this) }
    videos?.apply { onVideosLoaded(this) }
  }

  private fun onError(error: Throwable) = doIfViewReady {
    hideLoading()
    showError(error.message ?: "")
  }

  private fun loadVideos(movieId: Int) {
    addSubscription(interactor.getVideos(movieId).subscribe({ videos ->
      this.videos = videos
      onVideosLoaded(videos)
    }, { throwable ->
      onVideosLoaded(null)
    }))
  }

  private fun onVideosLoaded(videos: List<Video>?) = doIfViewReady {
    var hasVideos = false
    clearTrailersView()
    videos?.apply {
      // looking for trailer
      forEach {
        if (it.type.equals(Video.TYPE_TRAILER)) {
          trailer = it
          doIfViewReady { setTrailer(it) }
        }
      }

      // add all videos
      forEach {
        addVideo(it)
        hasVideos = true
      }
    }
    viewState.isLoadOnce = true
    viewState.isLoading = false
    setVisibility(trailer != null, hasVideos)
  }
}