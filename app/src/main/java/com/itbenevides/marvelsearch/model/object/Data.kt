package com.itbenevides.marvelsearch.model.`object`

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Data : Serializable {

    @SerializedName("offset")
    @Expose
    var offset: Int? = null
    @SerializedName("limit")
    @Expose
    var limit: Int? = null
    @SerializedName("total")
    @Expose
    var total: Int? = null
    @SerializedName("count")
    @Expose
    var count: Int? = null
    @SerializedName("results")
    @Expose
    var results: MutableList<Result>? = null

}