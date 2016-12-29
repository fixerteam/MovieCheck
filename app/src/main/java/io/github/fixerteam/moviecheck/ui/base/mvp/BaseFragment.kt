package io.github.fixerteam.moviecheck.ui.base.mvp

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.github.fixerteam.moviecheck.di.HasComponent

abstract class BaseFragment : Fragment() {

  protected abstract fun getLayout(): View

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) = getLayout()

  override fun onCreate(savedInstanceState: Bundle?) {
    injectDependencies()
    super.onCreate(savedInstanceState)
  }

  override fun onDetach() {
    super.onDetach()
  }

  override fun onDestroyView() {
    super.onDestroyView()
  }

  override fun onDestroy() {
    super.onDestroy()
    getPresenter()?.detachView()
  }

  protected abstract fun injectDependencies()

  @Suppress("UNCHECKED_CAST")
  protected fun <T> activityComponent() = (activity as HasComponent<T>).component()

  protected abstract fun getPresenter(): BasePresenter<*, *>?
}