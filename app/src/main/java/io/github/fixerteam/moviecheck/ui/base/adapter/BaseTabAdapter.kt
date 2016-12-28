package io.github.fixerteam.moviecheck.ui.base.adapter

import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import io.github.fixerteam.moviecheck.ui.base.mvp.BaseFragment
import java.util.*


class BaseTabAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {

  private val fragmentList: ArrayList<BaseFragment> = ArrayList()
  private val fragmentTitle: ArrayList<String> = ArrayList()

  override fun getItem(position: Int) = fragmentList[position]

  override fun getCount() = fragmentList.size

  override fun getPageTitle(position: Int) = fragmentTitle[position]

  fun add(fragment: BaseFragment, title: String) {
    fragmentList.add(fragment)
    fragmentTitle.add(title)
    notifyDataSetChanged()
  }
}