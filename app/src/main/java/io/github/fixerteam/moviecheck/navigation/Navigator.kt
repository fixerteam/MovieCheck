package io.github.fixerteam.moviecheck.navigation

import android.content.Context
import android.support.v4.app.FragmentManager
import io.github.fixerteam.moviecheck.R
import io.github.fixerteam.moviecheck.ui.base.mvp.BaseFragment
import io.github.fixerteam.moviecheck.ui.main.MainActivity
import org.jetbrains.anko.startActivity

class Navigator {
  companion object {

    const val MOVIE_ID_EXTRA = "movie_id_extra"
    const val CURRENT_FRAGMENT = "currentFragment"

    fun  showMovieDetail(context: Context?, idMovie: Int) {
      if (context is MainActivity) {
        context.startActivity<MainActivity>(MOVIE_ID_EXTRA to idMovie)
      }
    }

    private fun showFragment(activity: MainActivity, fragment: BaseFragment, needBack: Boolean = true) {
      val fm = activity.supportFragmentManager
      if (! needBack) {
        clearScreenHistory(fm)
      }
      val backStateName = fragment.javaClass.name
      val fragmentPopped = if (fm.backStackEntryCount != 0) fm.popBackStackImmediate(backStateName, 0) else false

      if (! fragmentPopped && fm.findFragmentByTag(backStateName) == null) {
        fm.beginTransaction()
            .replace(R.id.main_holder, fragment, backStateName)
            .addToBackStack(backStateName)
            .commit()
        activity.intent.putExtra(CURRENT_FRAGMENT, backStateName)
      }
      fm.executePendingTransactions()
    }

    private fun clearScreenHistory(fm: FragmentManager) {
      try {
        while (fm.backStackEntryCount != 0) {
          fm.popBackStackImmediate()
        }
      } catch (e: IllegalStateException) {
        e.printStackTrace()
      }
      fm.executePendingTransactions()
    }
  }
}