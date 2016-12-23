package io.github.fixerteam.moviecheck.ui.main

import io.github.fixerteam.moviecheck.App.Companion.appComponent
import io.github.fixerteam.moviecheck.R
import io.github.fixerteam.moviecheck.di.HasComponent
import io.github.fixerteam.moviecheck.ui.base.mvp.BaseActivity

class MainActivity : BaseActivity(), HasComponent<MainComponent> {

  override fun component(): MainComponent? = DaggerMainComponent.builder()
      .appComponent(appComponent)
      .mainModule(MainModule())
      .build()

  override fun getLayout() = R.layout.activity_main
}