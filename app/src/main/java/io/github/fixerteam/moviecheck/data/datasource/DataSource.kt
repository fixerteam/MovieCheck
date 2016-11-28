package io.github.fixerteam.moviecheck.data.datasource

import io.github.fixerteam.moviecheck.data.Movie

interface DataSource {

  fun getPopularMovies(): List<Movie>
}