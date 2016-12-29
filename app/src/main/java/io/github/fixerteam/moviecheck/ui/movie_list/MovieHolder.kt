package io.github.fixerteam.moviecheck.ui.movie_list

import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import io.github.fixerteam.moviecheck.R
import io.github.fixerteam.moviecheck.domain.pojo.Movie
import io.github.fixerteam.moviecheck.ui.base.adapter.RecyclerHolder
import io.github.fixerteam.moviecheck.util.loadUrl
import org.jetbrains.anko.find
import org.jetbrains.anko.onClick

class MovieHolder(parent: ViewGroup, private val listener: (movieId: Movie) -> Unit) : RecyclerHolder<Movie>(parent,
    R.layout.item_movie) {

  private val label = itemView.find<TextView>(R.id.label)
  private val image = itemView.find<ImageView>(R.id.image)

  override fun bind(item: Movie) {
    label.text = item.originalTitle
    image.loadUrl(item.posterPath)
    itemView.onClick { listener.invoke(item) }
  }
}