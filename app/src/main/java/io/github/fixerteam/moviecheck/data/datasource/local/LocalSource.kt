package io.github.fixerteam.moviecheck.data.datasource.local

import io.github.fixerteam.moviecheck.data.Movie
import io.github.fixerteam.moviecheck.data.datasource.DataSource

class LocalSource : DataSource {

  override fun getPopularMovies(): List<Movie> = emptyList()
}