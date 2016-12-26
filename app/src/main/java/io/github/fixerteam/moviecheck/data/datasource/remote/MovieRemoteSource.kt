package io.github.fixerteam.moviecheck.data.datasource.remote

import io.github.fixerteam.moviecheck.data.datasource.MovieDataSource
import io.github.fixerteam.moviecheck.data.datasource.local.local_model.MovieType
import io.github.fixerteam.moviecheck.domain.pojo.Movie
import rx.Observable

class MovieRemoteSource(private val api: Api) : MovieDataSource {

  override fun getMoviesByType(movieType: MovieType): Observable<List<Movie>> =
      api.discoverMovies(movieType.name.toLowerCase()).map { it?.results ?: emptyList() }

  override fun saveMoviesByType(movieType: MovieType, items: List<Movie>) {}
}