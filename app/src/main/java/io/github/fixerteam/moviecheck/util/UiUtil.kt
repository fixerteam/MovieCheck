package io.github.fixerteam.moviecheck.util

import android.support.annotation.LayoutRes
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide

fun View.hide() {
  visibility = GONE
}

fun View.show() {
  visibility = VISIBLE
}

fun ViewGroup.inflate(@LayoutRes layoutId: Int): View =
    LayoutInflater.from(context).inflate(layoutId, this, false)

fun ImageView.loadUrl(url: String?) {
  Glide.with(context).load("$url").into(this)
}

fun ImageView.loadUrl(url: String?, placeholderColor: Int) {
  Glide.with(context)
      .load("$url")
      .placeholder(placeholderColor)
      .centerCrop()
      .crossFade()
      .into(this)
}

fun Fragment.snackbar(message: String) {
  view?.apply {
    Snackbar.make(this, message, Snackbar.LENGTH_INDEFINITE).show()
  }
}