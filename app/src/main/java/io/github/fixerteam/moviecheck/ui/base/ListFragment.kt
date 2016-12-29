package io.github.fixerteam.moviecheck.ui.base

import android.os.Bundle
import android.support.annotation.CallSuper
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.TextView
import io.github.fixerteam.moviecheck.R
import io.github.fixerteam.moviecheck.ui.base.adapter.RecyclerAdapter
import io.github.fixerteam.moviecheck.ui.base.adapter.RecyclerHolder
import io.github.fixerteam.moviecheck.ui.base.mvp.BaseFragment
import io.github.fixerteam.moviecheck.util.hide
import io.github.fixerteam.moviecheck.util.show
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import kotlin.LazyThreadSafetyMode.NONE

abstract class ListFragment<in T, out VH : RecyclerHolder<T>> : BaseFragment(), ListView<T> {

  private lateinit var list: RecyclerView
  private lateinit var messageLabel: TextView
  private lateinit var progress: ContentLoadingProgressBar

  private val adapter by lazy(NONE) {
    object : RecyclerAdapter<T, VH>() {
      override fun getViewHolder(parent: ViewGroup): VH = this@ListFragment.getViewHolder(parent)
    }
  }

  override fun getLayout() = ListFragmentUi().createView(AnkoContext.create(context, this))

  @CallSuper
  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    list = view.find<RecyclerView>(R.id.list)
    messageLabel = view.find<TextView>(R.id.message_label)
    progress = view.find<ContentLoadingProgressBar>(R.id.content_progress)
  }

  override fun isReady() = isAdded

  override fun showLoading() = progress.show()

  override fun hideLoading() = progress.hide()

  override fun showEmpty(message: String) {
    list.hide()
    messageLabel.show()
    messageLabel.text = message
  }

  override fun showContent(content: List<T>) {
    adapter.setItems(content)
    list.show()
    list.adapter = adapter
    messageLabel.hide()
  }

  fun onRestoreStateFragment() {
    list.show()
    list.adapter = adapter
  }

  override fun showError(message: String) {
    list.hide()
    messageLabel.show()
    messageLabel.text = message
  }

  abstract fun getViewHolder(parent: ViewGroup): VH
}

class ListFragmentUi : AnkoComponent<Fragment> {
  override fun createView(ui: AnkoContext<Fragment>) = with(ui) {
    relativeLayout {
      recyclerView {
        id = R.id.list
        layoutManager = GridLayoutManager(context, 2)
        setItemViewCacheSize(50)
        lparams {
          width = MATCH_PARENT
          height = MATCH_PARENT
        }
      }
      textView {
        id = R.id.message_label
        lparams {
          centerInParent()
        }
      }
      include<ContentLoadingProgressBar>(R.layout.content_progress)
    }
  }
}