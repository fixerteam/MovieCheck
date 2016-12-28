package io.github.fixerteam.moviecheck.ui.main

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import io.github.fixerteam.moviecheck.App.Companion.appComponent
import io.github.fixerteam.moviecheck.R
import io.github.fixerteam.moviecheck.data.datasource.local.local_model.MovieType
import io.github.fixerteam.moviecheck.di.HasComponent
import io.github.fixerteam.moviecheck.ui.base.adapter.BaseTabAdapter
import io.github.fixerteam.moviecheck.ui.base.mvp.BaseActivity
import io.github.fixerteam.moviecheck.ui.movie_list.MovieFragment
import org.jetbrains.anko.find

class MainActivity : BaseActivity(), HasComponent<MainComponent> {

  private val viewPager by lazy { find<ViewPager>(R.id.viewPager) }
  private val tabLayout by lazy { find<TabLayout>(R.id.tabLayout) }
  private val pagerAdapter by lazy {
    BaseTabAdapter(supportFragmentManager).apply {
      add(MovieFragment.newInstance(MovieType.POPULAR), getString(R.string.text_popular))
      add(MovieFragment.newInstance(MovieType.LATEST), getString(R.string.text_last))
      add(MovieFragment.newInstance(MovieType.TOP_RATED), getString(R.string.text_top_rated))
    }
  }

  override fun component(): MainComponent? = DaggerMainComponent.builder()
      .appComponent(appComponent)
      .mainModule(MainModule())
      .build()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    initTabPager()
  }

  private fun initTabPager() {
    viewPager.adapter = pagerAdapter
    tabLayout.setupWithViewPager(viewPager)
  }

  override fun getLayout() = R.layout.activity_main
}