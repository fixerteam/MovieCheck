package io.github.fixerteam.moviecheck.data.datasource.local

import io.github.fixerteam.moviecheck.data.datasource.MovieDataSource
import io.github.fixerteam.moviecheck.data.datasource.local.local_model.MovieType
import io.github.fixerteam.moviecheck.data.datasource.local.local_model.RealmMovie
import io.github.fixerteam.moviecheck.domain.pojo.Movie
import io.realm.Realm
import rx.Observable
import java.util.*

class MovieLocalSource(private val db: Realm) : MovieDataSource {

  override fun getMoviesByType(movieType: MovieType): Observable<List<Movie>> {
    val realm = Realm.getDefaultInstance()
    val movies: MutableList<Movie> = ArrayList()
    realm.where(RealmMovie::class.java).findAll().forEach { movies.add(Movie(it)) }

    return Observable.just(movies)
  }

  override fun saveMoviesByType(movieType: MovieType, items: List<Movie>) {
    val realm = Realm.getDefaultInstance()
    realm.beginTransaction()
    items.forEach {
      var realmMovie = realm.where(RealmMovie::class.java).equalTo("id", it.id).findFirst()
      if (realmMovie == null) {
        realmMovie = realm.createObject(RealmMovie::class.java, it.id)
      }
      realmMovie.setFieldsFrom(it)
      realmMovie.type = movieType.toString().toLowerCase()
    }
    realm.commitTransaction()
  }

  override fun getMovie(movieId: Int): Observable<Movie> {
    val realmMovie = Realm.getDefaultInstance().where(RealmMovie::class.java).equalTo("id", movieId).findFirst()
    return Observable.just(Movie(realmMovie))
  }
}
