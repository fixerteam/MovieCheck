package io.github.fixerteam.moviecheck.data.datasource.local

import io.github.fixerteam.moviecheck.data.datasource.MovieDataSource
import io.github.fixerteam.moviecheck.data.datasource.local.local_model.MovieType
import io.github.fixerteam.moviecheck.data.datasource.local.local_model.RealmMovie
import io.github.fixerteam.moviecheck.domain.pojo.Movie
import io.github.fixerteam.moviecheck.domain.pojo.Video
import io.realm.Realm
import rx.Observable

/**
 * Локальный источник данных.
 * Основная задача - кэшировать данные из удаленного источника и отдавать их,
 * когда удаленный источник вернул ошибку (например отсутствие интернета).
 * Если потребуется поменять ORM, все изменения останутся в пределах данного класса.
 * Или же можно просто реализовать другой источник данных с нужной ORM и подложить в репозиторий через DI
 */
class MovieLocalSource() : MovieDataSource {

  override fun getVideos(movieId: Int): Observable<List<Video>> {
    TODO("implement getVideos function!")
  }

  override fun getMoviesByType(movieType: MovieType): Observable<List<Movie>> =
      Observable.fromCallable {
        Realm.getDefaultInstance()
            .where(RealmMovie::class.java)
            .equalTo("type", movieType.name)
            .findAll()
            .map(::Movie)
      }

  override fun saveMoviesByType(movieType: MovieType, items: List<Movie>) {
    Realm.getDefaultInstance().inTransaction { realm ->
      items.forEach {
        var realmMovie = realm.where(RealmMovie::class.java).equalTo("id", it.id).findFirst()
        if (realmMovie == null) {
          realmMovie = realm.createObject(RealmMovie::class.java, it.id)
        }
        realmMovie.setFieldsFrom(it)
        realmMovie.type = movieType.name
      }
    }
  }

  override fun getMovie(movieId: Int): Observable<Movie> =
      Realm.getDefaultInstance()
          .where(RealmMovie::class.java)
          .equalTo("id", movieId)
          .findFirst()
          .asObservable<RealmMovie>()
          .map(::Movie)
}

inline fun Realm.inTransaction(func: (realm: Realm) -> Unit) {
  beginTransaction()
  func.invoke(this)
  commitTransaction()
}