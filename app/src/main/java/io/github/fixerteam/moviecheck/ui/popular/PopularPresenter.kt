package io.github.fixerteam.moviecheck.ui.popular

import io.github.fixerteam.moviecheck.domain.MovieInteractor
import io.github.fixerteam.moviecheck.domain.pojo.Movie
import io.github.fixerteam.moviecheck.ui.base.mvp.BasePresenter
import io.github.fixerteam.moviecheck.ui.popular.PopularMoviesContract.Presenter
import io.github.fixerteam.moviecheck.ui.popular.PopularMoviesContract.View

class PopularPresenter(private val interactor: MovieInteractor) : BasePresenter<Movie, View<Movie>>(), Presenter {

  fun init() {

  }

  override fun onStart() {
    doIfViewReady { showLoading() }
    addSubscription(interactor.getPopular()
        .subscribe({ onMoviesLoaded(it) }, { onError(it) }))
  }

  override fun onSwipe() {
  }

  override fun onMovieSelected(movie: Movie) {
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