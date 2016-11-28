package io.github.fixerteam.moviecheck.domain

import io.github.fixerteam.moviecheck.data.Movie
import io.github.fixerteam.moviecheck.data.repository.MovieRepository
import org.jetbrains.anko.asyncResult
import org.jetbrains.anko.uiThread

class PopularMovieUseCase {

  private val repository by lazy { MovieRepository() }

  fun getPopular(callback: (result: List<Movie>) -> Unit) {
    asyncResult {
      val result = repository.getPopularMovies()
      uiThread { callback.invoke(result) }
    }
  }
}