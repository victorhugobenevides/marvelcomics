package com.itbenevides.marvelsearch

import android.content.Context
import android.os.AsyncTask
import com.itbenevides.marvelsearch.model.`object`.MarvelResult
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

open class MarvelSearchAsynk(private val query: String?, private val offset: Int, private val context: Context, private val returnDataInterface: ReturnDataInterface) : AsyncTask<Void, Void, String>() {


    lateinit var service: MarvelRetrofitInterface
    var result: MarvelResult? = null
    var resultError: Exception? = null

    override fun onPreExecute() {
        super.onPreExecute()

        val oktHttpClient = OkHttpClient.Builder()
                .addInterceptor(Interceptor(context)).build()


        val retrofit = Retrofit.Builder()
                .baseUrl(context.getString(R.string.url))
                .addConverterFactory(GsonConverterFactory.create())
                .client(oktHttpClient)
                .build()

        service = retrofit.create(MarvelRetrofitInterface::class.java)


    }


    override fun doInBackground(vararg params: Void?): String? {

        try {

            if (query == null)
                result = service.getAll(offset).execute().body()
            else
                result = service.getSearch(query, offset).execute().body()

        } catch (e: Exception) {
            resultError = e
            result = null

        }



        return null

    }

    override fun onPostExecute(r: String?) {
        super.onPostExecute(r)

        if (result != null)
            returnDataInterface.dataReturn(result?.data)
        else
            returnDataInterface.erroReturn(resultError)
    }


}