package io.github.fixerteam.moviecheck.ui.detail

import io.github.fixerteam.moviecheck.domain.MovieInteractor
import io.github.fixerteam.moviecheck.domain.pojo.Movie
import io.github.fixerteam.moviecheck.ui.base.mvp.BasePresenter
import io.github.fixerteam.moviecheck.ui.detail.DetailContract.Presenter
import io.github.fixerteam.moviecheck.ui.detail.DetailContract.View

class DetailPresenter(private val interactor: MovieInteractor) : BasePresenter<Movie, View<Movie>>(), Presenter {

  override fun onStart() {
    doIfViewReady { showLoading() }
//    addSubscription(interactor.getPopular()
//        .subscribe({ onMoviesLoaded(it) }, { onError(it) }))
  }

  private fun onError(error: Throwable) = doIfViewReady {
    hideLoading()
    showError(error.message ?: "")
  }

  private fun onMoviesLoaded(movies: List<Movie>) = doIfViewReady {
    hideLoading()
    if (movies.isEmpty()) {
      showEmpty("Content empty")
    } else {
      showContent(movies)
    }
  }
}