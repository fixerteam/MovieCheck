package io.github.fixerteam.moviecheck.ui.main

import android.content.Context
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import io.github.fixerteam.moviecheck.App.Companion.appComponent
import io.github.fixerteam.moviecheck.R
import io.github.fixerteam.moviecheck.data.datasource.local.local_model.MovieType
import io.github.fixerteam.moviecheck.di.HasComponent
import io.github.fixerteam.moviecheck.domain.pojo.Movie
import io.github.fixerteam.moviecheck.ui.base.adapter.BaseTabAdapter
import io.github.fixerteam.moviecheck.ui.base.mvp.BaseActivity
import io.github.fixerteam.moviecheck.ui.movie_list.MovieFragment
import org.jetbrains.anko.find
import javax.inject.Inject

class MainActivity : BaseActivity(), HasComponent<MainComponent>, MainContract.View<Movie> {

  private val viewPager by lazy { find<ViewPager>(R.id.viewPager) }
  private val tabLayout by lazy { find<TabLayout>(R.id.tabLayout) }
  private var twoPane = false

  @Inject lateinit var presenter: MainPresenter

  private val pagerAdapter by lazy {
    BaseTabAdapter(supportFragmentManager).apply {
      add(MovieFragment.newInstance(MovieType.POPULAR), getString(R.string.text_popular))
      add(MovieFragment.newInstance(MovieType.UPCOMING), getString(R.string.text_upcoming))
      add(MovieFragment.newInstance(MovieType.TOP_RATED), getString(R.string.text_top_rated))
    }
  }

  override fun component(): MainComponent? = DaggerMainComponent.builder()
      .appComponent(appComponent)
      .mainModule(MainModule())
      .build()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    injectComponents()
    twoPane = findViewById(R.id.detail_view) != null
    initPresenter(twoPane)
    initTabPager()
  }

  private fun injectComponents() {
    component()?.inject(this)
  }

  private fun initPresenter(twoPane: Boolean) {
    presenter.attachView(this)
    presenter.init(twoPane)
  }

  fun onMovieSelected(movie: Movie) {
    presenter.onMovieSelect(movie)
  }

  private fun initTabPager() {
    viewPager.adapter = pagerAdapter
    viewPager.offscreenPageLimit = 2
    tabLayout.setupWithViewPager(viewPager)
  }

  override fun isReady(): Boolean = !isDestroyed

  override fun showLoading() {
  }

  override fun showError(message: String) {
  }

  override fun context(): Context = this

  override fun hideLoading() {
  }

  override fun getLayout() = R.layout.activity_main
}