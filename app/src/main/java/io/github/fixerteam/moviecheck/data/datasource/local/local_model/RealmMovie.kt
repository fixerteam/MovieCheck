package io.github.fixerteam.moviecheck.data.datasource.local.local_model

import io.github.fixerteam.moviecheck.domain.pojo.Movie
import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass

@RealmClass
open class RealmMovie(
    @PrimaryKey open var id: Int = 0,
    open var posterPath: String? = null,
    open var adult: Boolean = false,
    open var overview: String? = null,
    open var releaseDate: String? = null,
    open var genreIds: RealmList<GenreId> = RealmList<GenreId>(),
    open var originalTitle: String? = null,
    open var originalLanguage: String? = null,
    open var title: String? = null,
    open var backdropPath: String? = null,
    open var popularity: Double = 0.0,
    open var voteCount: Int = 0,
    open var video: Boolean = false,
    open var voteAverage: Double = 0.0,
    open var favored: Boolean = false,
    open var type: String? = null
) : RealmObject() {

  fun setFieldsFrom(movie: Movie) {
//    this.id = movie.id
    this.posterPath = movie.posterPath
    this.adult = movie.adult
    this.overview = movie.overview
    this.releaseDate = movie.releaseDate
    this.originalTitle = movie.originalTitle
    this.originalLanguage = movie.originalLanguage
    this.title = movie.title
    this.backdropPath = movie.backdropPath
    this.popularity = movie.popularity
    this.voteCount = movie.voteCount
    this.video = movie.video
    this.voteAverage = movie.voteAverage
    this.favored = movie.favored
    if (movie.type == null) {
      this.type = null
    } else {
      this.type = movie.type.toString()
    }

    // deep copy of genre ids
    this.genreIds = RealmList<GenreId>()
    movie.genreIds.forEach { genreIds.add(GenreId(it)) }
  }
}