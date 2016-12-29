package io.github.fixerteam.moviecheck.ui.main

import io.github.fixerteam.moviecheck.domain.pojo.Movie
import io.github.fixerteam.moviecheck.navigation.Navigator
import io.github.fixerteam.moviecheck.ui.base.mvp.BasePresenter

class MainPresenter : BasePresenter<Movie, MainContract.View<Movie>>() {

  fun onMovieSelect(movie: Movie) {
    if (twoPane) {
      doIfViewReady { Navigator.showMovieDetailTwoPaneFragment(context(), movie.id) }
    } else {
      doIfViewReady { Navigator.showMovieDetail(context(), movie.id) }
    }
  }

  private var twoPane: Boolean = false

  fun init(twoPane: Boolean) {
    this.twoPane = twoPane
  }

}