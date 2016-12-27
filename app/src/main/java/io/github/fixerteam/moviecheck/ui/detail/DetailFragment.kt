package io.github.fixerteam.moviecheck.ui.detail

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import io.github.fixerteam.moviecheck.R
import io.github.fixerteam.moviecheck.domain.pojo.Movie
import io.github.fixerteam.moviecheck.ui.base.ContentLoadingProgressBar
import io.github.fixerteam.moviecheck.ui.base.mvp.BaseFragment
import io.github.fixerteam.moviecheck.ui.base.mvp.BasePresenter
import io.github.fixerteam.moviecheck.ui.main.MainComponent
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import javax.inject.Inject

class DetailFragment : BaseFragment(), DetailContract.View<Movie> {

  override fun getPresenter(): BasePresenter<Movie, DetailContract.View<Movie>> = presenter

  override fun isReady(): Boolean {
    //todo implement isReady function!
    throw UnsupportedOperationException("not implemented")
  }

  override fun showLoading() {
    //todo implement showLoading function!
    throw UnsupportedOperationException("not implemented")
  }

  override fun showEmpty(message: String) {
    //todo implement showEmpty function!
    throw UnsupportedOperationException("not implemented")
  }

  override fun showContent(content: List<Movie>) {
    //todo implement showContent function!
    throw UnsupportedOperationException("not implemented")
  }

  override fun showError(message: String) {
    //todo implement showError function!
    throw UnsupportedOperationException("not implemented")
  }

  override fun hideLoading() {
    //todo implement hideLoading function!
    throw UnsupportedOperationException("not implemented")
  }

  override fun getLayout(): View = DetailFragmentUi().createView(AnkoContext.create(context, this))

  override fun injectDependencies() {
    activityComponent<MainComponent>()
        ?.plusDetailMoviesSubComponent(DetailModule())
        ?.inject(this)
  }

  @Inject lateinit var presenter: DetailPresenter

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    presenter.attachView(this)
    presenter.onStart()
  }

  class DetailFragmentUi : AnkoComponent<Fragment> {
    override fun createView(ui: AnkoContext<Fragment>) = with(ui) {
      relativeLayout {
        recyclerView {
          id = R.id.list
          layoutManager = LinearLayoutManager(context)
          lparams {
            width = ViewGroup.LayoutParams.MATCH_PARENT
            height = ViewGroup.LayoutParams.MATCH_PARENT
          }
        }
        textView {
          id = R.id.message_label
          gravity = Gravity.CENTER
        }
        include<ContentLoadingProgressBar>(R.layout.content_progress)
      }
    }
  }
}