package io.github.fixerteam.moviecheck.util

import android.support.annotation.LayoutRes
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup

fun View.hide() {
  visibility = GONE
}

fun View.show() {
  visibility = VISIBLE
}

fun ViewGroup.inflate(@LayoutRes layoutId: Int): View =
    LayoutInflater.from(context).inflate(layoutId, this, false)