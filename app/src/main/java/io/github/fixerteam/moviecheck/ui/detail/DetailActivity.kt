package io.github.fixerteam.moviecheck.ui.detail

import android.os.Bundle
import io.github.fixerteam.moviecheck.App.Companion.appComponent
import io.github.fixerteam.moviecheck.R
import io.github.fixerteam.moviecheck.di.HasComponent
import io.github.fixerteam.moviecheck.ui.base.mvp.BaseActivity
import io.github.fixerteam.moviecheck.ui.main.DaggerMainComponent
import io.github.fixerteam.moviecheck.ui.main.MainComponent
import io.github.fixerteam.moviecheck.ui.main.MainModule

class DetailActivity : BaseActivity(), HasComponent<MainComponent> {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    val movieId: Int = intent.getIntExtra(DetailFragment.MOVIE_ID, 277834)
    val fragment = DetailFragment.newInstance(movieId)
    supportFragmentManager.beginTransaction().replace(R.id.frame, fragment).commit()
  }

  override fun component(): MainComponent? = DaggerMainComponent.builder()
      .appComponent(appComponent)
      .mainModule(MainModule())
      .build()

  override fun getLayout() = R.layout.activity_detail
}
