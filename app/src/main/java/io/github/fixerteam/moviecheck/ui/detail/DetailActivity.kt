package io.github.fixerteam.moviecheck.ui.detail

import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import io.github.fixerteam.moviecheck.App.Companion.appComponent
import io.github.fixerteam.moviecheck.R
import io.github.fixerteam.moviecheck.di.HasComponent
import io.github.fixerteam.moviecheck.ui.base.mvp.BaseActivity
import io.github.fixerteam.moviecheck.ui.main.DaggerMainComponent
import io.github.fixerteam.moviecheck.ui.main.MainComponent
import io.github.fixerteam.moviecheck.ui.main.MainModule
import io.github.fixerteam.moviecheck.ui.navigation.MOVIE_ID_EXTRA
import io.github.fixerteam.moviecheck.ui.navigation.showMovieDetailFragment
import org.jetbrains.anko.find

class DetailActivity : BaseActivity(), HasComponent<MainComponent> {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    if (savedInstanceState == null) {
      val movieId: Int = intent.getIntExtra(MOVIE_ID_EXTRA, 277834)
      showMovieDetailFragment(this, movieId)
    }

    val toolbar = find<Toolbar>(R.id.toolbar)
    setSupportActionBar(toolbar)
    supportActionBar?.setDisplayHomeAsUpEnabled(true)
    supportActionBar?.setDisplayShowHomeEnabled(true)
  }

  override fun component(): MainComponent? = DaggerMainComponent.builder()
      .appComponent(appComponent)
      .mainModule(MainModule())
      .build()

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    if (item.itemId === android.R.id.home) {
      finish()
    }

    return super.onOptionsItemSelected(item)
  }

  override fun getLayout() = R.layout.activity_detail
}
