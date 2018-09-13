package com.itbenevides.marvelsearch.presenter

import com.itbenevides.marvelsearch.MainActivity
import com.itbenevides.marvelsearch.ReturnDataInterface
import com.itbenevides.marvelsearch.model.ModelMain
import com.itbenevides.marvelsearch.model.`object`.Data
import com.itbenevides.marvelsearch.view.ViewMain

class PresenterMain(private val viewMain: ViewMain, private val activity: MainActivity) : ReturnDataInterface {


    var mLoading: Boolean = false
    var totalItem: Int = 0
    var offset: Int = 0
    var modelMain: ModelMain = ModelMain(activity)
    var query:String?=null

    fun scrolledRecyclerView(lastVisibleItem: Int, visibleItemCount: Int, totalIt: Int, query: String?) {

        if (!mLoading && (lastVisibleItem + visibleItemCount) >= totalIt - 1 && totalIt < totalItem) {
            mLoading = true
            offset = totalIt
            viewMain.showLoadingDialog()
            this.query=query
            modelMain.loadData(query, totalIt, this)

        }
    }

    fun refrashData(){
        offset=0
        totalItem = 0
        viewMain.showLoadingDialog()
        modelMain.loadData(query , offset, this)
    }

    fun loadData():Boolean {
        offset = 0
        totalItem = 0
        viewMain.showLoadingDialog()
        this.query=null
        modelMain.loadData(null, offset, this)

        return true
    }

    fun SearchData(query: String?) {
        offset = 0
        totalItem = 0
        viewMain.showLoadingDialog()
        this.query=query
        modelMain.loadData(query, offset, this)
    }

    override fun dataReturn(data: Data?) {
        mLoading = false
        viewMain.dismissLoadingDialog()

        if (offset == 0) {
            totalItem = data?.total!!
            viewMain.loadRecyclerViewData(data.results)
        } else
            viewMain.addItensRecyclerViewData(data?.results)



    }

    override fun erroReturn(exception: Exception?) {
        mLoading = false
        viewMain.dismissLoadingDialog()
        viewMain.showAlertErrorResult(exception)


    }


}