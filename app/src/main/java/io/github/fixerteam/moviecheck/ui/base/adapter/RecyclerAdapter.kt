package io.github.fixerteam.moviecheck.ui.base.adapter

import android.support.annotation.LayoutRes
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import io.github.fixerteam.moviecheck.util.inflate
import java.util.*
import kotlin.LazyThreadSafetyMode.NONE

abstract class RecyclerAdapter<in T, VH : RecyclerHolder<T>> : RecyclerView.Adapter<VH>() {

  private val contentList by lazy(NONE) { ArrayList<T>() }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = getViewHolder(parent)

  override fun onBindViewHolder(holder: VH, position: Int) = holder.bind(contentList[position])

  override fun getItemCount() = contentList.size

  abstract fun getViewHolder(parent: ViewGroup): VH

  fun setItems(items: List<T>) = with(contentList) {
    if (isNotEmpty()) {
      clear()
    }
    addAll(items)
    notifyDataSetChanged()
  }
}

abstract class RecyclerHolder<in T>(parent: ViewGroup, @LayoutRes layout: Int) :
    RecyclerView.ViewHolder(parent.inflate(layout)) {

  abstract fun bind(item: T)
}