package io.github.fixerteam.moviecheck.di

import android.content.Context
import dagger.Module
import dagger.Provides
import io.realm.Realm
import javax.inject.Singleton

@Module class DbModule {

  @Provides @Singleton fun provideRealm(context: Context): Realm {
    Realm.init(context)
    return Realm.getDefaultInstance()
  }
}