package io.github.fixerteam.moviecheck.ui.base

import io.github.fixerteam.moviecheck.ui.base.mvp.BaseView

interface ListView<in T> : BaseView<T> {

  fun showContent(content: List<T>)

  fun showEmpty(message: String)
}