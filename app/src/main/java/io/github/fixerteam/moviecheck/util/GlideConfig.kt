package io.github.fixerteam.moviecheck.util

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.integration.okhttp3.OkHttpUrlLoader
import com.bumptech.glide.load.model.GenericLoaderFactory
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.ModelLoader
import com.bumptech.glide.load.model.ModelLoaderFactory
import com.bumptech.glide.load.model.stream.BaseGlideUrlLoader
import com.bumptech.glide.module.GlideModule
import io.github.fixerteam.moviecheck.App
import okhttp3.OkHttpClient
import java.io.InputStream
import javax.inject.Inject

class GlideConfig : GlideModule {

  @Inject lateinit var okHttp: OkHttpClient

  override fun registerComponents(context: Context?, glide: Glide?) {
    App.appComponent.inject(this)

    glide?.register(String::class.java, InputStream::class.java, ImageLoader.Factory())
    glide?.register(GlideUrl::class.java, InputStream::class.java, OkHttpUrlLoader.Factory(okHttp))
  }

  override fun applyOptions(context: Context?, builder: GlideBuilder?) {}
}

class ImageLoader(context: Context) : BaseGlideUrlLoader<String>(context) {
  override fun getUrl(model: String, width: Int, height: Int) = buildPosterPath(model, width)

  class Factory : ModelLoaderFactory<String, InputStream> {
    override fun teardown() {}

    override fun build(context: Context, factories: GenericLoaderFactory?): ModelLoader<String, InputStream> {
      return ImageLoader(context)
    }
  }
}