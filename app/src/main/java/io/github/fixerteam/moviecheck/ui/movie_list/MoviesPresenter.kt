package io.github.fixerteam.moviecheck.ui.movie_list

import android.os.Bundle
import io.github.fixerteam.moviecheck.data.datasource.local.local_model.MovieType
import io.github.fixerteam.moviecheck.domain.MovieInteractor
import io.github.fixerteam.moviecheck.domain.pojo.Movie
import io.github.fixerteam.moviecheck.navigation.Navigator
import io.github.fixerteam.moviecheck.ui.base.mvp.BasePresenter
import io.github.fixerteam.moviecheck.ui.movie_list.MoviesContract.Presenter
import io.github.fixerteam.moviecheck.ui.movie_list.MoviesContract.View

class MoviesPresenter(private val interactor: MovieInteractor) : BasePresenter<Movie, View<Movie>>(), Presenter {

  val viewState: ViewState = ViewState()
  var type: MovieType = MovieType.POPULAR

  companion object {
    val TYPE_FILTER_EXTRA = "type_filter_extra"
  }

  override fun init(arguments: Bundle) {
    type = arguments.getSerializable(TYPE_FILTER_EXTRA) as MovieType
    if (! viewState.isLoadOnce || viewState.isLoading) {
      doIfViewReady { showLoading() }
    }

    loadData(false)

    if (viewState.isLoadOnce && ! viewState.isLoading) {
      restoreState()
    }
  }

  private fun restoreState() {
    if (viewState.isEmpty) {
      doIfViewReady { showEmpty("Content empty") }
    } else {
      doIfViewReady { onRestoreState() }
    }
  }

  private fun loadData(isForce: Boolean) {
    if (! viewState.isLoading && ! viewState.isLoadOnce || isForce) {
      viewState.isLoading = true
      addSubscription(when (type) {
        MovieType.POPULAR -> {
          interactor.getPopular()
        }
        MovieType.UPCOMING -> {
          interactor.getUpcoming()
        }
        MovieType.TOP_RATED -> {
          interactor.getTopRated()
        }
      }.subscribe({ onMoviesLoaded(it) }, { onError(it) }))
    }
  }

  override fun onSwipe() = loadData(true)

  override fun onMovieSelected(movie: Movie) = doIfViewReady {
    Navigator.showMovieDetail(context(), movie.id)
  }

  private fun onError(error: Throwable) = doIfViewReady {
    hideLoading()
    showError(error.message ?: "")
  }

  private fun onMoviesLoaded(movies: List<Movie>) = doIfViewReady {
    hideLoading()
    if (movies.isEmpty()) {
      viewState.isEmpty = true
      showEmpty("Content empty")
    } else {
      showContent(movies)
    }
    viewState.isLoadOnce = true
    viewState.isLoading = false
  }
}