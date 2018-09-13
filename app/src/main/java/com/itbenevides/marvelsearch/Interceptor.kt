package com.itbenevides.marvelsearch

import android.content.Context
import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.HttpUrl
import java.util.*


class Interceptor(private val context: Context): Interceptor{

    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val originalHttpUrl = original.url()

        val timeStamp = Calendar.getInstance().timeInMillis

        val url = originalHttpUrl.newBuilder()
                .addQueryParameter("apikey", context.getString(R.string.api_public_key_marvel))
                .addQueryParameter("ts", timeStamp.toString())
                .addQueryParameter("hash", Utils().md5(timeStamp.toString()+context.getString(R.string.api_private_key_marvel)+context.getString(R.string.api_public_key_marvel)))
                .build()

        val requestBuilder = original.newBuilder()
                .url(url)

        val request = requestBuilder.build()
        return chain.proceed(request)
    }

}