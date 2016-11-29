package io.github.fixerteam.moviecheck.data.repository

import io.github.fixerteam.moviecheck.data.datasource.remote.RemoteSource

class MovieRepository {

  fun getPopularMovies() = RemoteSource.getPopularMovies()
}