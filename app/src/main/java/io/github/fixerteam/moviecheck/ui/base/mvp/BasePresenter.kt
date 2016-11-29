package io.github.fixerteam.moviecheck.ui.base.mvp

abstract class BasePresenter<in T, V : BaseView<T>> {

  private var view: V? = null

  fun attachView(view: V) {
    this.view = view
  }

  fun detachView() {
    this.view = null
  }

  fun doIfViewReady(body: V.() -> Unit) {
    view?.apply {
      if (isReady()) {
        body.invoke(this)
      }
    }
  }
}