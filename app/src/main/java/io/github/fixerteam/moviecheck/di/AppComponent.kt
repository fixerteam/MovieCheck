package io.github.fixerteam.moviecheck.di

import com.photy.data.NetworkModule
import dagger.Component
import io.github.fixerteam.moviecheck.domain.MovieInteractor
import io.github.fixerteam.moviecheck.util.GlideConfig
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AppModule::class, NetworkModule::class, DbModule::class, DataModule::class, DomainModel::class))
interface AppComponent {

  fun inject(config: GlideConfig)

  fun movieInteractor(): MovieInteractor
}