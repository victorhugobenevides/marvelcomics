package com.itbenevides.marvelsearch.model.`object`

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class ItemStories : Serializable {

    @SerializedName("resourceURI")
    @Expose
    var resourceURI: String? = null
    @SerializedName("name")
    @Expose
    var name: String? = null
    @SerializedName("type")
    @Expose
    var type: String? = null

}