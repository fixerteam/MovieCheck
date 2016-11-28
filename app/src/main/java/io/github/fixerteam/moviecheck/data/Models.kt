package io.github.fixerteam.moviecheck.data

import org.json.JSONObject

data class Movie(val poster_path: String, val adult: Boolean, val overview: String, val release_date: String) {

  companion object Builder {
    fun fromJson(json: JSONObject) = Movie(
        json.optString("poster_path"),
        json.optBoolean("adult"),
        json.optString("overview"),
        json.optString("release_date"))
  }
}