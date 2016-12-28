package io.github.fixerteam.moviecheck.ui.detail

import android.util.Log
import io.github.fixerteam.moviecheck.domain.MovieInteractor
import io.github.fixerteam.moviecheck.domain.pojo.Movie
import io.github.fixerteam.moviecheck.domain.pojo.Video
import io.github.fixerteam.moviecheck.ui.base.mvp.BasePresenter
import io.github.fixerteam.moviecheck.ui.detail.DetailContract.Presenter
import io.github.fixerteam.moviecheck.ui.detail.DetailContract.View

class DetailPresenter(private val interactor: MovieInteractor) : BasePresenter<Movie, View<Movie>>(), Presenter {

  private var mVideos: List<Video>? = null
  private var mTrailer: Video? = null

  override fun showDetail(movieId: Int) {
    addSubscription(interactor.getMovie(movieId)
        .doOnNext { loadVideos(movieId) }
        .subscribe({ doIfViewReady { showDetail(it) } }, { onError(it) }))
  }

  override fun onStart() {
    doIfViewReady { showLoading() }
  }

  private fun onError(error: Throwable) = doIfViewReady {
    hideLoading()
    showError(error.message ?: "")
  }

  private fun loadVideos(movieId: Int) {
    addSubscription(interactor.getVideos(movieId).subscribe({ videos ->
      onVideosLoaded(videos)
    }, { throwable ->
      onVideosLoaded(null)
    }))
  }

  private fun onVideosLoaded(videos: List<Video>?) {
    mVideos = videos

    // Remove all existing videos (everything but first two children)
    doIfViewReady {
      removeVideos()
      var hasVideos = false

      if (mVideos != null && mVideos!!.isNotEmpty()) {

        // looking for trailer
        for (video in mVideos!!) {
          if (video.type.equals(Video.TYPE_TRAILER)) {
            Log.d("DETAIL", "Found trailer!")
            mTrailer = video
            doIfViewReady { setTrailer(video) }
            break
          }
        }

        // add all videos
        for (video in videos!!) {
          addVideo(video)
          hasVideos = true
        }
      }

      setVisibility(mTrailer != null, hasVideos)
    }
  }
}