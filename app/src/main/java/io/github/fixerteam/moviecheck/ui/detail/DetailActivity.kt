package io.github.fixerteam.moviecheck.ui.detail

import android.os.Bundle
import io.github.fixerteam.moviecheck.App.Companion.appComponent
import io.github.fixerteam.moviecheck.R
import io.github.fixerteam.moviecheck.di.HasComponent
import io.github.fixerteam.moviecheck.ui.base.mvp.BaseActivity
import io.github.fixerteam.moviecheck.ui.main.DaggerMainComponent
import io.github.fixerteam.moviecheck.ui.main.MainComponent
import io.github.fixerteam.moviecheck.ui.main.MainModule
import io.github.fixerteam.moviecheck.ui.navigation.MOVIE_ID_EXTRA
import io.github.fixerteam.moviecheck.ui.navigation.showMovieDetailFragment

class DetailActivity : BaseActivity(), HasComponent<MainComponent> {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    if (savedInstanceState == null) {
      val movieId: Int = intent.getIntExtra(MOVIE_ID_EXTRA, 277834)
      showMovieDetailFragment(this, movieId)
    }
  }

  override fun component(): MainComponent? = DaggerMainComponent.builder()
      .appComponent(appComponent)
      .mainModule(MainModule())
      .build()

  override fun getLayout() = R.layout.activity_detail
}
