package io.github.fixerteam.moviecheck.data.repository

import io.github.fixerteam.moviecheck.data.datasource.MovieDataSource
import io.github.fixerteam.moviecheck.data.datasource.local.local_model.MovieType
import io.github.fixerteam.moviecheck.domain.pojo.Movie
import io.github.fixerteam.moviecheck.domain.pojo.Video
import rx.Observable

/**
 * Репозиторий управляет источниками данных и решает откуда брать запрашиваемую информацию.
 * Выделяют 3 типа источников данных:
 *  1.локальный (бд, файлы)
 *  2.удаленный (сеть)
 *  3.память
 *
 * Для каждого источника можно определить время жизни кэша.
 * Основная задача репозитория - отдать данные. Откуда он возьмет их, решать только ему.
 * Так же именно репозиторий следит за актуализацией кэшей.
 */
class MovieRepository(
    private val localSource: MovieDataSource,
    private val remoteSource: MovieDataSource) {

  fun getMovieByType(movieType: MovieType): Observable<List<Movie>> =
      remoteSource.getMoviesByType(movieType)
          .doOnNext { localSource.saveMoviesByType(movieType, it) }
          .onErrorResumeNext { localSource.getMoviesByType(movieType) }

  fun getMovie(movieId: Int): Observable<Movie> = localSource.getMovie(movieId)

  fun getVideos(movieId: Int): Observable<List<Video>> = remoteSource.getVideos(movieId)
}