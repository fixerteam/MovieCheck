package io.github.fixerteam.moviecheck.data.repository

import io.github.fixerteam.moviecheck.data.datasource.MovieDataSource
import io.github.fixerteam.moviecheck.data.datasource.local.local_model.MovieType
import io.github.fixerteam.moviecheck.domain.pojo.Movie
import rx.Observable

class MovieRepository(
    private val localSource: MovieDataSource,
    private val remoteSource: MovieDataSource) {

  fun getMovieByType(movieType: MovieType): Observable<List<Movie>> =
      remoteSource.getMoviesByType(movieType)
          .doOnNext {
            localSource.saveMoviesByType(movieType, it)
          }
          .onErrorResumeNext {
            localSource.getMoviesByType(movieType)
          }

  fun getMovie(movieId: Int): Observable<Movie> = localSource.getMovie(movieId)
}