package io.github.fixerteam.moviecheck.data.datasource.remote

import io.github.fixerteam.moviecheck.data.datasource.MovieDataSource
import io.github.fixerteam.moviecheck.data.datasource.local.local_model.MovieType
import io.github.fixerteam.moviecheck.domain.pojo.Movie
import io.github.fixerteam.moviecheck.domain.pojo.Video
import rx.Observable
import java.util.concurrent.TimeUnit

class MovieRemoteSource(private val api: Api) : MovieDataSource {

  override fun getMovie(movieId: Int): Observable<Movie> {
    //todo implement getMovie function!
    throw UnsupportedOperationException("not implemented")
  }

  override fun getMoviesByType(movieType: MovieType): Observable<List<Movie>> =
      api.discoverMovies(movieType.name.toLowerCase()).map { it?.results ?: emptyList() }

  override fun saveMoviesByType(movieType: MovieType, items: List<Movie>) {}

  override fun getVideos(movieId: Int): Observable<List<Video>> {
    return api.videos(movieId)
        .timeout(2, TimeUnit.SECONDS)
        .retry(2)
        .map(Video.Response::results)
  }
}