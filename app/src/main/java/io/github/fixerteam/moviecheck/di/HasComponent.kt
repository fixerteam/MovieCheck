package io.github.fixerteam.moviecheck.di

interface HasComponent<out T> {
  fun component(): T?
}