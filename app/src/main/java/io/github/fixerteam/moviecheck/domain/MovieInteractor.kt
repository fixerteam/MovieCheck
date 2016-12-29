package io.github.fixerteam.moviecheck.domain

import io.github.fixerteam.moviecheck.data.datasource.local.local_model.MovieType
import io.github.fixerteam.moviecheck.data.repository.MovieRepository
import io.github.fixerteam.moviecheck.domain.pojo.Movie
import io.github.fixerteam.moviecheck.domain.pojo.Video
import rx.Observable
import rx.android.schedulers.AndroidSchedulers.mainThread
import rx.schedulers.Schedulers.io

/**
 * Интерактор является посредником между presentation слоем и репозиториями.
 * Основная задача - инкапсулировать внутри себя бизнес-логику приложения. Например,
 * собирать одну сущность из разных запросов, работать с аналитикой.
 * Также инкапсулирует внутри себя всю работу с потоками (Thread).
 */
class MovieInteractor(private val movieRepository: MovieRepository) {

  fun getPopular() = inBackground { movieRepository.getMovieByType(MovieType.POPULAR) }

  fun getUpcoming() = inBackground { movieRepository.getMovieByType(MovieType.UPCOMING) }

  fun getTopRated() = inBackground { movieRepository.getMovieByType(MovieType.TOP_RATED) }

  fun getMovie(movieId: Int): Observable<Movie> = inBackground { movieRepository.getMovie(movieId) }

  fun getVideos(movieId: Int): Observable<List<Video>> = inBackground { movieRepository.getVideos(movieId) }

  private inline fun <T> inBackground(func: () -> Observable<T>): Observable<T> =
      func.invoke().subscribeOn(io()).observeOn(mainThread())
}