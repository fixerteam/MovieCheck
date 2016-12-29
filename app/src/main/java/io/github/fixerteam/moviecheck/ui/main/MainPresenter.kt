package io.github.fixerteam.moviecheck.ui.main

import io.github.fixerteam.moviecheck.domain.pojo.Movie
import io.github.fixerteam.moviecheck.ui.base.mvp.BasePresenter
import io.github.fixerteam.moviecheck.ui.navigation.showMovieDetail
import io.github.fixerteam.moviecheck.ui.navigation.showMovieDetailTwoPaneFragment

class MainPresenter : BasePresenter<Movie, MainContract.View<Movie>>() {

  fun onMovieSelect(movie: Movie) {
    if (twoPane) {
      doIfViewReady { showMovieDetailTwoPaneFragment(context(), movie.id) }
    } else {
      doIfViewReady { showMovieDetail(context(), movie.id) }
    }
  }

  private var twoPane: Boolean = false

  fun init(twoPane: Boolean) {
    this.twoPane = twoPane
  }

}