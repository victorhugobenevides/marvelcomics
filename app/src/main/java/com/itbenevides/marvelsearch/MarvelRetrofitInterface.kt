package com.itbenevides.marvelsearch


import com.itbenevides.marvelsearch.model.`object`.MarvelResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query



interface MarvelRetrofitInterface {
    @GET("/v1/public/characters?limit=50")
    fun getSearch(@Query("nameStartsWith") name: String,@Query("offset") offset:Int): Call<MarvelResult>

    @GET("/v1/public/characters?limit=50")
    fun getAll(@Query("offset") offset:Int): Call<MarvelResult>

}
