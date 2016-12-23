package io.github.fixerteam.moviecheck.ui.popular

import android.view.ViewGroup
import android.widget.TextView
import io.github.fixerteam.moviecheck.R
import io.github.fixerteam.moviecheck.domain.pojo.Movie
import io.github.fixerteam.moviecheck.ui.base.adapter.RecyclerHolder
import org.jetbrains.anko.find

class MovieHolder(parent: ViewGroup) :
    RecyclerHolder<Movie>(parent, R.layout.item_popular_movie) {

  private val label = itemView.find<TextView>(R.id.label)

  override fun bind(item: Movie) {
    label.text = item.overview
  }
}