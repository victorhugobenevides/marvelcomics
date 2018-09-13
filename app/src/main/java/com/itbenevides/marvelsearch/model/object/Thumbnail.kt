package com.itbenevides.marvelsearch.model.`object`

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Thumbnail : Serializable {

    @SerializedName("path")
    @Expose
    var path: String? = null
    @SerializedName("extension")
    @Expose
    var extension: String? = null



}