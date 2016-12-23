package io.github.fixerteam.moviecheck.data.datasource

import io.github.fixerteam.moviecheck.domain.pojo.Movie
import rx.Observable

interface MovieDataSource {

  fun getMoviesByType(movieType: Movie.Type): Observable<List<Movie>>

  fun saveMoviesByType(movieType: Movie.Type, items: List<Movie>)
}