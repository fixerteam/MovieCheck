package io.github.fixerteam.moviecheck.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import io.github.fixerteam.moviecheck.R
import io.github.fixerteam.moviecheck.domain.PopularMovieUseCase
import org.jetbrains.anko.find
import org.jetbrains.anko.indeterminateProgressDialog

class MainActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    val textView = find<TextView>(R.id.text_test)
    val progressDialog = indeterminateProgressDialog("Loading")
    progressDialog.show()
    PopularMovieUseCase().getPopular {
      textView.text = it.toString()
      progressDialog.dismiss()
    }
  }
}