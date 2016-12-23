package io.github.fixerteam.moviecheck.di

import dagger.Module
import dagger.Provides
import io.github.fixerteam.moviecheck.data.repository.MovieRepository
import io.github.fixerteam.moviecheck.domain.MovieInteractor
import javax.inject.Singleton

@Module
class DomainModel {

  @Singleton
  @Provides
  fun provideMovieInteractor(movieRepository: MovieRepository) = MovieInteractor(movieRepository)
}