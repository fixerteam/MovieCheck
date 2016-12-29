package io.github.fixerteam.moviecheck.data.datasource.remote

import io.github.fixerteam.moviecheck.data.datasource.MovieDataSource
import io.github.fixerteam.moviecheck.data.datasource.local.local_model.MovieType
import io.github.fixerteam.moviecheck.domain.pojo.Movie
import io.github.fixerteam.moviecheck.domain.pojo.Video
import rx.Observable
import java.util.concurrent.TimeUnit

/**
 * Удаленный источник данных.
 * Основная задача - обеспечить поставку данных из сети. Является оберткой над rest клиентом,
 * формирует запросы и парсит ответы.
 * Если требуется поменять формат общения с сервером или же библиотеку rest клиента, меняем данную обертку,
 * либо реализуем новую и подкладываем в репозиторий через DI
 */
class MovieRemoteSource(private val api: Api) : MovieDataSource {

  override fun getMovie(movieId: Int): Observable<Movie> {
    //todo implement getMovie function!
    throw UnsupportedOperationException("not implemented")
  }

  override fun getMoviesByType(movieType: MovieType): Observable<List<Movie>> =
      api.discoverMovies(movieType.name.toLowerCase()).map { it?.results ?: emptyList() }

  override fun saveMoviesByType(movieType: MovieType, items: List<Movie>) {
  }

  override fun getVideos(movieId: Int): Observable<List<Video>> =
      api.videos(movieId)
          .timeout(2, TimeUnit.SECONDS)
          .retry(2)
          .map(Video.Response::results)
}