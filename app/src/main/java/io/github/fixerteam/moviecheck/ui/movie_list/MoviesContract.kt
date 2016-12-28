package io.github.fixerteam.moviecheck.ui.movie_list

import android.os.Bundle
import io.github.fixerteam.moviecheck.domain.pojo.Movie
import io.github.fixerteam.moviecheck.ui.base.mvp.BaseView

interface MoviesContract {

  interface Presenter {
    fun init(arguments: Bundle)
    fun onSwipe()
    fun onMovieSelected(movie: Movie)
  }

  interface View<in T> : BaseView<T> {
    fun onRestoreState()
  }
}