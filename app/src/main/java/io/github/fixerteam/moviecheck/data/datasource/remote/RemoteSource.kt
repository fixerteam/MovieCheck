package io.github.fixerteam.moviecheck.data.datasource.remote

import io.github.fixerteam.moviecheck.BuildConfig.API_KEY
import io.github.fixerteam.moviecheck.BuildConfig.API_URL
import io.github.fixerteam.moviecheck.data.Movie
import io.github.fixerteam.moviecheck.data.datasource.DataSource
import org.json.JSONObject
import java.net.URL

object RemoteSource : DataSource {

  override fun getPopularMovies(): List<Movie> {
    val response = URL("${API_URL}movie/popular?api_key=$API_KEY&language=ru-RU").readText()
    val results = JSONObject(response).getJSONArray("results")
    return (0..results.length())
        .map { results.optJSONObject(it) }
        .filter { it != null }
        .map { Movie.fromJson(it) }
  }
}