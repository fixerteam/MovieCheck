package io.github.fixerteam.moviecheck

import android.app.Application
import com.photy.data.NetworkModule
import io.github.fixerteam.moviecheck.di.*
import io.realm.Realm
import io.realm.RealmConfiguration

open class App : Application() {

  companion object {
    lateinit var appComponent: AppComponent
  }

  override fun onCreate() {
    super.onCreate()
    initDependencyGraph()
    iniRealm()
  }

  protected fun initDependencyGraph() {
    appComponent = DaggerAppComponent.builder()
        .dataModule(DataModule())
        .domainModel(DomainModel())
        .networkModule(NetworkModule())
        .appModule(AppModule(this))
        .build()
  }

  private fun iniRealm() {
    Realm.init(this)
    Realm.setDefaultConfiguration(RealmConfiguration.Builder()
        .name(Realm.DEFAULT_REALM_NAME)
        .schemaVersion(1)
        .deleteRealmIfMigrationNeeded()
        .build())
  }
}