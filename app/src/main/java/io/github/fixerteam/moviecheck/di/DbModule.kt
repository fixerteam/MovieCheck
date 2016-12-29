package io.github.fixerteam.moviecheck.di

import android.content.Context
import dagger.Module
import dagger.Provides
import io.realm.Realm
import io.realm.RealmConfiguration
import javax.inject.Singleton

@Module class DbModule {

  @Provides @Singleton fun provideRealm(context: Context): Realm {

    Realm.init(context)
    val realmConfiguration = RealmConfiguration.Builder()
        .name(Realm.DEFAULT_REALM_NAME)
        .schemaVersion(1)
        .deleteRealmIfMigrationNeeded()
        .build()
    Realm.setDefaultConfiguration(realmConfiguration)
    return Realm.getDefaultInstance()
  }
}