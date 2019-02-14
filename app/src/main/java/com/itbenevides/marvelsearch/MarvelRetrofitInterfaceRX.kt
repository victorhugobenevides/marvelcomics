package com.itbenevides.marvelsearch


import com.itbenevides.marvelsearch.model.`object`.MarvelResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.*


interface MarvelRetrofitInterfaceRX {
    @GET("/v1/public/characters?limit=50")
    fun getSearch(@Query("nameStartsWith") name: String,@Query("offset") offset:Int): rx.Observable<MarvelResult>

    @GET("/v1/public/characters?limit=50")
    fun getAll(@Query("offset") offset:Int): rx.Observable<MarvelResult>

}
