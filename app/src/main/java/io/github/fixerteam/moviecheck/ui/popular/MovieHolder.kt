package io.github.fixerteam.moviecheck.ui.popular

import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import io.github.fixerteam.moviecheck.R
import io.github.fixerteam.moviecheck.domain.pojo.Movie
import io.github.fixerteam.moviecheck.ui.base.adapter.RecyclerHolder
import io.github.fixerteam.moviecheck.util.loadUrl
import org.jetbrains.anko.*

class MovieHolder(parent: ViewGroup) : RecyclerHolder<Movie>(MovieViewItem().createView(AnkoContext.Companion.create(parent.context, parent))) {

  private val label = itemView.find<TextView>(R.id.label)
  private val image = itemView.find<ImageView>(R.id.image)

  override fun bind(item: Movie) {
    label.text = item.overview
    image.loadUrl(item.posterPath)
  }
}

class MovieViewItem : AnkoComponent<ViewGroup> {
  override fun createView(ui: AnkoContext<ViewGroup>) = with(ui) {
    verticalLayout {
      imageView { id = R.id.image }
      textView { id = R.id.label }
    }
  }
}