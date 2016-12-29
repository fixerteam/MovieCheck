package io.github.fixerteam.moviecheck.ui.base.mvp

import android.content.Context

/**
 * Базовый интерфейс для всех view в приложении. В MVP view могут быть Activity, Fragment, View.
 * Передают действия пользователя в презентер и отрисовывают себя по команде презентера
 */
interface BaseView<in T> {

  fun isReady(): Boolean

  fun showLoading()

  fun showError(message: String)

  fun context(): Context

  fun hideLoading()
}