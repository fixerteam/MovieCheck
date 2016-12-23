package io.github.fixerteam.moviecheck.domain

import io.github.fixerteam.moviecheck.data.repository.MovieRepository
import io.github.fixerteam.moviecheck.domain.pojo.Movie
import rx.Observable
import rx.android.schedulers.AndroidSchedulers.mainThread
import rx.schedulers.Schedulers.io

class MovieInteractor(private val movieRepository: MovieRepository) {

  fun getPopular() = inBackground { movieRepository.getMovieByType(Movie.Type.POPULAR) }

  fun getLatest() = inBackground { movieRepository.getMovieByType(Movie.Type.LATEST) }

  fun getTopRated() = inBackground { movieRepository.getMovieByType(Movie.Type.TOP_RATED) }

  private inline fun <T> inBackground(func: () -> Observable<T>): Observable<T> =
      func.invoke().subscribeOn(io()).observeOn(mainThread())
}