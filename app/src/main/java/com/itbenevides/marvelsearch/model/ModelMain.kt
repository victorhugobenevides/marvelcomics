package com.itbenevides.marvelsearch.model

import android.content.Context
import com.itbenevides.marvelsearch.MarvelSearchAsynk
import com.itbenevides.marvelsearch.MarvelSearchRX
import com.itbenevides.marvelsearch.ReturnDataInterface

class ModelMain(private val context: Context){


    fun loadData(query:String?,offset:Int,returnDataInterface: ReturnDataInterface){
        MarvelSearchRX(query, offset, context, returnDataInterface).execute()
    }


}