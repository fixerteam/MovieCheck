package io.github.fixerteam.moviecheck.domain.pojo

import android.os.Parcel
import android.os.Parcelable
import io.github.fixerteam.moviecheck.data.datasource.local.local_model.MovieType
import io.github.fixerteam.moviecheck.data.datasource.local.local_model.RealmMovie
import java.util.*

class Movie() : Parcelable {

  var posterPath: String? = null
  var adult: Boolean = false
  var overview: String? = null
  var releaseDate: String? = null
  var genreIds: List<Int> = emptyList()
  var id: Int = 0
  var originalTitle: String? = null
  var originalLanguage: String? = null
  var title: String? = null
  var backdropPath: String? = null
  var popularity: Double = 0.0
  var voteCount: Int = 0
  var video: Boolean = false
  var voteAverage: Double = 0.0
  var favored = false
  var type: MovieType? = null

  companion object {
    val CREATOR: Parcelable.Creator<Movie> = object : Parcelable.Creator<Movie> {
      override fun createFromParcel(source: Parcel): Movie = Movie(source)
      override fun newArray(size: Int): Array<Movie?> = arrayOfNulls(size)
    }
  }

  constructor(realmMovie: RealmMovie) : this() {
    this.id = realmMovie.id
    this.posterPath = realmMovie.posterPath
    this.adult = realmMovie.adult
    this.overview = realmMovie.overview
    this.releaseDate = realmMovie.releaseDate
    this.originalTitle = realmMovie.originalTitle
    this.originalLanguage = realmMovie.originalLanguage
    this.title = realmMovie.title
    this.backdropPath = realmMovie.backdropPath
    this.popularity = realmMovie.popularity
    this.voteCount = realmMovie.voteCount
    this.video = realmMovie.video
    this.voteAverage = realmMovie.voteAverage
    this.favored = realmMovie.favored
    if (realmMovie.type == null) {
      this.type = null
    } else {
      this.type =  MovieType.valueOf(realmMovie.type!!.toUpperCase())
    }

    // deep copy of genre ids
    this.genreIds = ArrayList()
    realmMovie.genreIds.toList().forEach { (genreIds as ArrayList<Int>).add(it.id) }
  }

  constructor(source: Parcel) : this() {
    this.posterPath = source.readString()
    this.adult = source.readByte().toInt() != 0
    this.overview = source.readString()
    this.releaseDate = source.readString()
    this.genreIds = ArrayList<Int>()
    source.readList(this.genreIds, List::class.java.classLoader)
    this.id = source.readInt()
    this.originalTitle = source.readString()
    this.originalLanguage = source.readString()
    this.title = source.readString()
    this.backdropPath = source.readString()
    this.popularity = source.readDouble()
    this.voteCount = source.readInt()
    this.video = source.readByte().toInt() != 0
    this.voteAverage = source.readDouble()
    this.favored = source.readByte().toInt() != 0
    this.type = MovieType.valueOf(source.readString())
  }

  override fun describeContents() = 0

  override fun writeToParcel(dest: Parcel?, flags: Int) {
    dest?.writeString(this.posterPath)
    dest?.writeByte(if (adult) 1.toByte() else 0.toByte())
    dest?.writeString(this.overview)
    dest?.writeString(this.releaseDate)
    dest?.writeList(this.genreIds)
    dest?.writeInt(this.id)
    dest?.writeString(this.originalTitle)
    dest?.writeString(this.originalLanguage)
    dest?.writeString(this.title)
    dest?.writeString(this.backdropPath)
    dest?.writeDouble(this.popularity)
    dest?.writeInt(this.voteCount)
    dest?.writeByte(if (video) 1.toByte() else 0.toByte())
    dest?.writeDouble(this.voteAverage)
    dest?.writeByte(if (favored) 1.toByte() else 0.toByte())
    dest?.writeString(this.type.toString())
  }

  class Response {
    var page: Int = 0
    var totalPages: Int = 0
    var totalResults: Int = 0
    var results: List<Movie> = emptyList()
  }
}