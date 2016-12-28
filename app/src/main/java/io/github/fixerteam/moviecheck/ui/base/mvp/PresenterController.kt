package io.github.fixerteam.moviecheck.ui.base.mvp

import android.os.Bundle
import java.util.*
import java.util.concurrent.atomic.AtomicLong

object PresenterController {

  private val PRESENTER_ID = "presenter_id"
  private val MAX_SIZE = 10
  private val currentId = AtomicLong(0)

  private val cache = object : LinkedHashMap<Long, BasePresenter<*, *>>(MAX_SIZE + 1, .75f, true) {
    override fun removeEldestEntry(eldest: MutableMap.MutableEntry<Long, BasePresenter<*, *>>?): Boolean {
      return size > MAX_SIZE
    }
  }

  fun restorePresenter(bundle: Bundle?): BasePresenter<*, *>? {
    val idPresenter = bundle?.getLong(PRESENTER_ID, 0)
    return cache[idPresenter]
  }

  fun putPresenter(presenter: BasePresenter<*, *>, bundle: Bundle?) {
    val idPresenter = currentId.incrementAndGet()
    cache.put(idPresenter, presenter)
    bundle?.putLong(PRESENTER_ID, idPresenter)
  }

  fun restorePresenter(id: Long) = cache[id]

  fun putPresenter(presenter: BasePresenter<Any, BaseView<Any>>): Long {
    val idPresenter = currentId.incrementAndGet()
    cache.put(idPresenter, presenter)
    return idPresenter
  }
}