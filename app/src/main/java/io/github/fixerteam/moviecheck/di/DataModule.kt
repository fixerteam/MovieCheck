package io.github.fixerteam.moviecheck.di

import dagger.Module
import dagger.Provides
import io.github.fixerteam.moviecheck.data.datasource.MovieDataSource
import io.github.fixerteam.moviecheck.data.datasource.local.MovieLocalSource
import io.github.fixerteam.moviecheck.data.datasource.remote.Api
import io.github.fixerteam.moviecheck.data.datasource.remote.MovieRemoteSource
import io.github.fixerteam.moviecheck.data.repository.MovieRepository
import javax.inject.Named
import javax.inject.Singleton

@Module
class DataModule {

  @Singleton
  @Provides
  @Named("local")
  fun provideLocalSource(): MovieDataSource = MovieLocalSource()

  @Singleton
  @Provides
  @Named("remote")
  fun provideRemoteSource(api: Api): MovieDataSource = MovieRemoteSource(api)

  @Singleton
  @Provides
  fun provideMovieRepository(@Named("local") localSource: MovieDataSource,
      @Named("remote") remoteSource: MovieDataSource) = MovieRepository(localSource, remoteSource)
}