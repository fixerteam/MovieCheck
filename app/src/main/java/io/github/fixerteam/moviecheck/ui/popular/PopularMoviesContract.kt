package io.github.fixerteam.moviecheck.ui.popular

import io.github.fixerteam.moviecheck.domain.pojo.Movie
import io.github.fixerteam.moviecheck.ui.base.mvp.BaseView

interface PopularMoviesContract {

  interface Presenter {
    fun onStart()
    fun onSwipe()
    fun onMovieSelected(movie: Movie)
  }

  interface View<in T> : BaseView<T>
}