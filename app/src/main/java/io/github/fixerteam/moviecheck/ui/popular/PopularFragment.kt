package io.github.fixerteam.moviecheck.ui.popular

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import io.github.fixerteam.moviecheck.data.Movie
import io.github.fixerteam.moviecheck.ui.base.ListFragment
import kotlin.LazyThreadSafetyMode.NONE

class PopularFragment : ListFragment<Movie, MovieHolder>(), PopularMoviesContract.View<Movie> {

  private val presenter by lazy(NONE) { PopularPresenter() }

  override fun getViewHolder(parent: ViewGroup) = MovieHolder(parent)

  override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
    presenter.attachView(this)
    presenter.onStart()
  }
}