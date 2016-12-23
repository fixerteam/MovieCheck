package io.github.fixerteam.moviecheck.data.datasource.local

import io.github.fixerteam.moviecheck.data.datasource.MovieDataSource
import io.github.fixerteam.moviecheck.domain.pojo.Movie
import io.realm.Realm
import rx.Observable

class MovieLocalSource(private val db: Realm) : MovieDataSource {
  override fun getMoviesByType(movieType: Movie.Type): Observable<List<Movie>> {
    TODO("implements with Realm")
  }

  override fun saveMoviesByType(movieType: Movie.Type, items: List<Movie>) {
    TODO("implements with Realm")
  }
}