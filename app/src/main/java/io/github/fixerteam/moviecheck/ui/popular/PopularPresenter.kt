package io.github.fixerteam.moviecheck.ui.popular

import io.github.fixerteam.moviecheck.data.Movie
import io.github.fixerteam.moviecheck.domain.PopularMovieUseCase
import io.github.fixerteam.moviecheck.ui.base.mvp.BasePresenter
import io.github.fixerteam.moviecheck.ui.popular.PopularMoviesContract.Presenter
import io.github.fixerteam.moviecheck.ui.popular.PopularMoviesContract.View
import kotlin.LazyThreadSafetyMode.NONE

class PopularPresenter : BasePresenter<Movie, View<Movie>>(), Presenter {

  private val useCase: PopularMovieUseCase by lazy(NONE) { PopularMovieUseCase() }

  override fun onStart() {
    doIfViewReady { showLoading() }
    useCase.getPopular {
      doIfViewReady {
        hideLoading()
        if (it.isEmpty()) {
          showEmpty("Content empty")
        } else {
          showContent(it)
        }
      }
    }
  }

  override fun onSwipe() {
  }

  override fun onMovieSelected(movie: Movie) {
  }
}