package com.itbenevides.marvelsearch.model.`object`

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Series : Serializable {

    @SerializedName("available")
    @Expose
    var available: Int? = null
    @SerializedName("collectionURI")
    @Expose
    var collectionURI: String? = null
    @SerializedName("items")
    @Expose
    var items: List<ItemSeries>? = null
    @SerializedName("returned")
    @Expose
    var returned: Int? = null

}