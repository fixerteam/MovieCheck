package io.github.fixerteam.moviecheck.ui.navigation

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.support.annotation.LayoutRes
import io.github.fixerteam.moviecheck.R
import io.github.fixerteam.moviecheck.ui.base.mvp.BaseActivity
import io.github.fixerteam.moviecheck.ui.base.mvp.BaseFragment
import io.github.fixerteam.moviecheck.ui.detail.DetailActivity
import io.github.fixerteam.moviecheck.ui.detail.DetailFragment
import io.github.fixerteam.moviecheck.ui.main.MainActivity
import org.jetbrains.anko.startActivity

/**
 * Навигатор служит для инкапсуляции переходов между экранами приложения.
 * Переходом может считаться как открытие новой Activity, так и отображение другого Fragment-а или View
 */

const val MOVIE_ID_EXTRA = "movie_id_extra"
const val CURRENT_FRAGMENT = "currentFragment"

fun showMovieDetail(context: Context?, idMovie: Int) {
  if (context is MainActivity) {
    if (context.findViewById(R.id.detail_view) != null) {
      showMovieDetailTwoPaneFragment(context, idMovie)
    } else {
      context.startActivity<DetailActivity>(MOVIE_ID_EXTRA to idMovie)
    }
  }
}

fun showMovieDetailFragment(context: Context?, idMovie: Int) {
  if (context is BaseActivity) {
    showFragment(context, DetailFragment.newInstance(idMovie), R.id.frame)
  }
}

fun showYouTube(context: Context?, videoKey: String?) {
  videoKey?.apply {
    context?.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com/watch?v=$videoKey")))
  }
}

private fun showMovieDetailTwoPaneFragment(context: Context?, idMovie: Int) {
  if (context is BaseActivity) {
    showFragment(context, DetailFragment.newInstance(idMovie), R.id.detail_view)
  }
}

private fun showFragment(activity: BaseActivity, fragment: BaseFragment, @LayoutRes layout: Int) {
  activity.supportFragmentManager.apply {
    beginTransaction()
        .replace(layout, fragment)
        .commit()
    executePendingTransactions()
  }
}