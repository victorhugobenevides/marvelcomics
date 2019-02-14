package com.itbenevides.marvelsearch

import android.content.Context
import com.itbenevides.marvelsearch.model.`object`.MarvelResult
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import rx.Observable
import rx.Observer
import rx.Scheduler
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.functions.Action1
import rx.schedulers.Schedulers

open class MarvelSearchRX(private val query: String?, private val offset: Int, private val context: Context, private val returnDataInterface: ReturnDataInterface){

    lateinit var service: MarvelRetrofitInterfaceRX
    var observable:Observable<MarvelResult>?=null

     fun execute() {


        val oktHttpClient = OkHttpClient.Builder()
                .addInterceptor(Interceptor(context)).build()


        val retrofit = Retrofit.Builder()
                .baseUrl(context.getString(R.string.url))
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(oktHttpClient)
                .build()

        service = retrofit.create(MarvelRetrofitInterfaceRX::class.java)


         if (query == null)
             observable=service.getAll(offset)
         else
             observable=service.getSearch(query, offset)



         observable!!.subscribeOn(Schedulers.newThread())
                 .observeOn(AndroidSchedulers.mainThread())
                 .subscribe(object:Observer<MarvelResult>{
                     override fun onNext(result: MarvelResult?) {
                         if (result != null)
                             returnDataInterface.dataReturn(result?.data)
                     }
                     override fun onCompleted() {


                     }

                     override fun onError(e: Throwable?) {
                         returnDataInterface.erroReturn(e as Exception?)
                     }


                 })




    }




}