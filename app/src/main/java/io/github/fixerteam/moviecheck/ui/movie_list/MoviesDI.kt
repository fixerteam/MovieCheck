package io.github.fixerteam.moviecheck.ui.movie_list

import dagger.Module
import dagger.Provides
import dagger.Subcomponent
import io.github.fixerteam.moviecheck.domain.MovieInteractor
import io.github.fixerteam.moviecheck.ui.main.MainScope

@MainScope
@Subcomponent(modules = arrayOf(PopularMoviesModule::class))
interface PopularMoviesSubComponent {

  fun inject(movieFragment: MovieFragment)
}

@Module
class PopularMoviesModule {

  @MainScope
  @Provides
  fun popularMoviesPresenter(interactor: MovieInteractor) = MoviesPresenter(interactor)
}