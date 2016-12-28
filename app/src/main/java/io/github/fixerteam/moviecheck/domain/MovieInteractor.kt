package io.github.fixerteam.moviecheck.domain

import io.github.fixerteam.moviecheck.data.datasource.local.local_model.MovieType
import io.github.fixerteam.moviecheck.data.repository.MovieRepository
import io.github.fixerteam.moviecheck.domain.pojo.Movie
import rx.Observable
import rx.android.schedulers.AndroidSchedulers.mainThread
import rx.schedulers.Schedulers.io

class MovieInteractor(private val movieRepository: MovieRepository) {

  fun getPopular() = inBackground { movieRepository.getMovieByType(MovieType.POPULAR) }

  fun getLatest() = inBackground { movieRepository.getMovieByType(MovieType.LATEST) }

  fun getTopRated() = inBackground { movieRepository.getMovieByType(MovieType.TOP_RATED) }

  private inline fun <T> inBackground(func: () -> Observable<T>): Observable<T> =
      func.invoke().subscribeOn(io()).observeOn(mainThread())

  fun getMovie(movieId: Int): Observable<Movie> = inBackground { movieRepository.getMovie(movieId) }
}