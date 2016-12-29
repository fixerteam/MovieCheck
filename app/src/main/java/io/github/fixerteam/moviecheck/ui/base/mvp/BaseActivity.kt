package io.github.fixerteam.moviecheck.ui.base.mvp

import android.os.Bundle
import android.support.annotation.CallSuper
import android.support.annotation.LayoutRes
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import io.github.fixerteam.moviecheck.R
import org.jetbrains.anko.find

abstract class BaseActivity : AppCompatActivity() {

  @LayoutRes abstract fun getLayout(): Int

  val toolbar by lazy { find<Toolbar>(R.id.toolbar) }

  @CallSuper
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(getLayout())
    initToolbar()
  }

  private fun initToolbar() {
    setSupportActionBar(toolbar)
  }
}