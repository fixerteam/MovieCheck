package io.github.fixerteam.moviecheck.data.repository

import io.github.fixerteam.moviecheck.data.datasource.local.LocalSource
import io.github.fixerteam.moviecheck.data.datasource.remote.RemoteSource

class MovieRepository {

  private val localSource by lazy { LocalSource() }
  private val remoteSource by lazy { RemoteSource() }

  fun getPopularMovies() = remoteSource.getPopularMovies()
}