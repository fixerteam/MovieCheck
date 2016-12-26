package io.github.fixerteam.moviecheck.ui.main

import android.os.Bundle
import io.github.fixerteam.moviecheck.App.Companion.appComponent
import io.github.fixerteam.moviecheck.R
import io.github.fixerteam.moviecheck.di.HasComponent
import io.github.fixerteam.moviecheck.navigation.Navigator
import io.github.fixerteam.moviecheck.ui.base.mvp.BaseActivity

class MainActivity : BaseActivity(), HasComponent<MainComponent> {

  override fun component(): MainComponent? = DaggerMainComponent.builder()
      .appComponent(appComponent)
      .mainModule(MainModule())
      .build()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    Navigator.showPopularVideo(this)
  }

  override fun getLayout() = R.layout.activity_main
}