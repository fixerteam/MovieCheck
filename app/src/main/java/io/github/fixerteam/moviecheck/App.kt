package io.github.fixerteam.moviecheck

import android.app.Application
import com.photy.data.NetworkModule
import io.github.fixerteam.moviecheck.di.*

open class App : Application() {

  companion object {
    lateinit var appComponent: AppComponent
  }

  override fun onCreate() {
    super.onCreate()
    initDependencyGraph()
  }

  protected fun initDependencyGraph() {
    appComponent = DaggerAppComponent.builder()
        .dataModule(DataModule())
        .domainModel(DomainModel())
        .networkModule(NetworkModule())
        .dbModule(DbModule())
        .appModule(AppModule(this))
        .build()
  }
}