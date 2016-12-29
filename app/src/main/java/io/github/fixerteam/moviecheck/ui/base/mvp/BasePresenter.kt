package io.github.fixerteam.moviecheck.ui.base.mvp

import rx.Subscription
import rx.subscriptions.CompositeSubscription
import kotlin.LazyThreadSafetyMode.NONE

/**
 * Презентеры управляют состоянием view и обрабатывают действия пользователей (клик, свайп).
 * Все необходимые данные получают от интеракторов
 */
abstract class BasePresenter<in T, V : BaseView<T>> {

  private var view: V? = null
  private val subscriptions by lazy(NONE) { CompositeSubscription() }

  fun attachView(view: V) {
    this.view = view
  }

  fun detachView() {
    view = null
    subscriptions.clear()
  }

  fun doIfViewReady(body: V.() -> Unit) {
    view?.apply {
      if (isReady()) {
        body.invoke(this)
      }
    }
  }

  fun addSubscription(subscription: Subscription) {
    subscriptions.add(subscription)
  }

  /**
   * Класс для сохранения состояния отображения
   */
  class ViewState {
    var isLoading = false
    var isLoadOnce = false
    var isEmpty = false
  }
}