package io.github.fixerteam.moviecheck.ui.base.mvp

import android.content.Context

interface BaseView<in T> {

  fun isReady(): Boolean

  fun showLoading()

  fun showEmpty(message: String)

  fun showContent(content: List<T>)

  fun showError(message: String)

  fun context(): Context

  fun hideLoading()
}