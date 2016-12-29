package io.github.fixerteam.moviecheck.ui.detail

import io.github.fixerteam.moviecheck.domain.pojo.Movie
import io.github.fixerteam.moviecheck.domain.pojo.Video
import io.github.fixerteam.moviecheck.ui.base.mvp.BasePresenter
import io.github.fixerteam.moviecheck.ui.base.mvp.BaseView

interface DetailContract {

  interface Presenter {
    fun onStart()
    fun showDetail(movieId: Int)
    fun playVideo(video: Video)
  }

  interface View<in T> : BaseView<T> {
    fun getPresenter() : BasePresenter<Movie, View<Movie>>
    fun showDetail(movie: Movie)
    fun clearTrailersView()
    fun setTrailer(trailer: Video)
    fun addVideo(video: Video)
    fun setVisibility(hasTrailer: Boolean, hasVideos: Boolean)
    fun playVideo(video: Video)
  }
}
