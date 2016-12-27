package io.github.fixerteam.moviecheck.ui.detail

import io.github.fixerteam.moviecheck.domain.pojo.Movie
import io.github.fixerteam.moviecheck.ui.base.mvp.BasePresenter
import io.github.fixerteam.moviecheck.ui.base.mvp.BaseView

interface DetailContract {

  interface Presenter {
    fun onStart()
  }

  interface View<in T> : BaseView<T> {
    fun getPresenter() : BasePresenter<Movie, View<Movie>>
  }
}
