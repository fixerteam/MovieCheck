package io.github.fixerteam.moviecheck.data.datasource.remote

import io.github.fixerteam.moviecheck.domain.pojo.Movie
import io.github.fixerteam.moviecheck.domain.pojo.Review
import io.github.fixerteam.moviecheck.domain.pojo.Video
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import rx.Observable

interface Api {

  @GET("movie/{type}")
  fun discoverMovies(@Path("type") movieType: String): Observable<Movie.Response>

  @GET("movie/{id}/videos")
  fun videos(@Path("id") movieId: Int): Observable<Video.Response>

  @GET("movie/{id}/reviews")
  fun reviews(@Path("id") movieId: Int, @Query("page") page: Int): Observable<Review.Response>
}