package io.github.fixerteam.moviecheck.util

const val IMAGE_URL = "http://image.tmdb.org/t/p"

fun buildPosterPath(path: String, width: Int) = IMAGE_URL + when (width) {
  in 0..92 -> "/w92"
  in 92..154 -> "/w154"
  in 154..185 -> "/w185"
  in 185..342 -> "/w342"
  in 342..500 -> "/w500"
  else -> "/w780"
} + path