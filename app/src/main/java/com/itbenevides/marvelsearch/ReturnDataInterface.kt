package com.itbenevides.marvelsearch

import com.itbenevides.marvelsearch.model.`object`.Data


interface ReturnDataInterface {
    fun dataReturn(data: Data?)
    fun erroReturn(exception: Exception?)
}