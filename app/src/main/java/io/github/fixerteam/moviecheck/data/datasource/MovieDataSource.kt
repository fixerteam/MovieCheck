package io.github.fixerteam.moviecheck.data.datasource

import io.github.fixerteam.moviecheck.data.datasource.local.local_model.MovieType
import io.github.fixerteam.moviecheck.domain.pojo.Movie
import rx.Observable

interface MovieDataSource {

  fun getMoviesByType(movieType: MovieType): Observable<List<Movie>>

  fun saveMoviesByType(movieType: MovieType, items: List<Movie>)
}