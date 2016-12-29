package io.github.fixerteam.moviecheck.ui.detail

import dagger.Module
import dagger.Provides
import dagger.Subcomponent
import io.github.fixerteam.moviecheck.domain.MovieInteractor
import io.github.fixerteam.moviecheck.ui.main.MainScope

@MainScope
@Subcomponent(modules = arrayOf(DetailModule::class))
interface DetailSubComponent {

  fun inject(popularFragment: DetailFragment)
}

@Module
class DetailModule {

  @MainScope
  @Provides
  fun popularMoviesPresenter(interactor: MovieInteractor) = DetailPresenter(interactor)
}