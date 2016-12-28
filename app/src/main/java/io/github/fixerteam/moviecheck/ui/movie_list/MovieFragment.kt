package io.github.fixerteam.moviecheck.ui.movie_list

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import io.github.fixerteam.moviecheck.data.datasource.local.local_model.MovieType
import io.github.fixerteam.moviecheck.domain.pojo.Movie
import io.github.fixerteam.moviecheck.ui.base.ListFragment
import io.github.fixerteam.moviecheck.ui.base.mvp.BasePresenter
import io.github.fixerteam.moviecheck.ui.main.MainComponent
import io.github.fixerteam.moviecheck.ui.movie_list.MoviesPresenter.Companion.TYPE_FILTER_EXTRA
import org.jetbrains.anko.bundleOf
import javax.inject.Inject

class MovieFragment : ListFragment<Movie, MovieHolder>(), MoviesContract.View<Movie> {

  companion object {
    fun newInstance(type: MovieType) = MovieFragment().apply {
      arguments = bundleOf(TYPE_FILTER_EXTRA to type)
    }
  }

  @Inject lateinit var presenter: MoviesPresenter

  override fun getViewHolder(parent: ViewGroup) = MovieHolder(parent)

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    retainInstance = true
  }

  override fun onStop() {
    super.onStop()
  }

  override fun onStart() {
    super.onStart()
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    presenter.attachView(this)
    presenter.init(arguments)
  }

  override fun onRestoreState() {
    onRestoreStateFragment()
  }

  override fun context(): Context = context

  override fun getPresenter(): BasePresenter<*, *>? = presenter

  override fun injectDependencies() {
    activityComponent<MainComponent>()
        ?.plusPopularMoviesSubComponent(PopularMoviesModule())
        ?.inject(this)
  }
}