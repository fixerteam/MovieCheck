package io.github.fixerteam.moviecheck.data.datasource.remote

import io.github.fixerteam.moviecheck.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class ApiKeyInterceptor : Interceptor {
  override fun intercept(chain: Interceptor.Chain): Response =
      chain.proceed(chain.request()
          .let {
            it.newBuilder()
                .url(it.url()
                    .newBuilder()
                    .addQueryParameter("api_key", BuildConfig.API_KEY)
                    .build())
                .build()
          })
}