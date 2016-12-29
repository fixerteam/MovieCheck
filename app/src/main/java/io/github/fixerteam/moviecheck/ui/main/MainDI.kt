package io.github.fixerteam.moviecheck.ui.main

import dagger.Component
import dagger.Module
import io.github.fixerteam.moviecheck.di.AppComponent
import io.github.fixerteam.moviecheck.ui.detail.DetailModule
import io.github.fixerteam.moviecheck.ui.detail.DetailSubComponent
import io.github.fixerteam.moviecheck.ui.movie_list.PopularMoviesModule
import io.github.fixerteam.moviecheck.ui.movie_list.PopularMoviesSubComponent
import javax.inject.Scope

@MainScope
@Component(dependencies = arrayOf(AppComponent::class), modules = arrayOf(MainModule::class))
interface MainComponent {
  fun plusPopularMoviesSubComponent(popularMoviesModule: PopularMoviesModule): PopularMoviesSubComponent
  fun plusDetailMoviesSubComponent(detailModule: DetailModule): DetailSubComponent
}

@Module
class MainModule {
}

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class MainScope