package com.itbenevides.marvelsearch.view

import android.content.Intent
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import android.view.Menu
import android.view.View
import com.itbenevides.marvelsearch.*
import com.itbenevides.marvelsearch.presenter.PresenterMain
import com.itbenevides.marvelsearch.model.`object`.Result
import kotlinx.android.synthetic.main.activity_main.*


class ViewMain(private val activity: MainActivity) : OnClickInterface {


    var recyclerView: RecyclerView? = null
    var searchView: SearchView? = null
    var alertDialog: AlertDialog? = null
    var layoutManager: LinearLayoutManager = LinearLayoutManager(activity)
    var list: MutableList<Result>? = null
    var resultProgress: Result = Result()

    var controlller: PresenterMain = PresenterMain(this, activity)

    var query: String? = null

    init {
        activity.setContentView(R.layout.activity_main)

        recyclerView = activity.heroes_recycler
        activity.supportActionBar?.setDisplayShowHomeEnabled(true)
        activity.supportActionBar?.setIcon(R.mipmap.ic_launcher)

        controlller.loadData()

    }


    fun loadRecyclerViewData(list: MutableList<Result>?) {
        if (list == null || list.isEmpty()) {
            showAlertEmptyResult()
        } else {
            this.list = list
            recyclerView?.adapter = MarvelAdapter(list, activity, this)
            recyclerView?.layoutManager = layoutManager
            recyclerView?.addOnScrollListener(object : RecyclerView.OnScrollListener() {

                override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    controlller.scrolledRecyclerView(layoutManager.findFirstVisibleItemPosition(), layoutManager.childCount, layoutManager.itemCount, query)
                }
            })
        }
    }


    fun addItensRecyclerViewData(list: MutableList<Result>?) {


        this.list?.addAll(list!!)
        recyclerView?.adapter?.notifyDataSetChanged()

    }

    fun loadMenu(menu: Menu) {

        val search = menu.findItem(R.id.search)


        searchView = search.actionView as SearchView

        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(qry: String): Boolean {

                query = qry
                clearRecyclerView()
                controlller.SearchData(qry)

                return false
            }


            override fun onQueryTextChange(newText: String): Boolean {

                query = newText

                return true
            }
        })


        searchView?.addOnAttachStateChangeListener(object : View.OnAttachStateChangeListener {

            override fun onViewDetachedFromWindow(arg0: View) {
                query = null
                clearRecyclerView()
                controlller.loadData()
            }

            override fun onViewAttachedToWindow(arg0: View) {

            }
        })

        searchView?.setOnCloseListener { false }
    }

    public fun OnClickrefrashButton() {

        clearRecyclerView()
        controlller.refrashData()
    }


    override fun onClick(var1: View, marvelChars: Result) {
        val intent = Intent(activity, DetailActivity::class.java)
        intent.putExtra("char", marvelChars)
        activity.startActivity(intent)
    }


    private fun showAlertEmptyResult() {
        if(alertDialog==null)
        alertDialog = AlertDialog.Builder(activity).create()
        alertDialog?.setTitle("Hello")
        alertDialog?.setMessage("empty result data!")
        alertDialog?.setButton(AlertDialog.BUTTON_NEUTRAL, "Ok"
        ) { dialog, which -> dialog.dismiss() }
        alertDialog?.show()
    }

    fun showAlertErrorResult(e: Exception?) {

        if(alertDialog==null)
        alertDialog = AlertDialog.Builder(activity).create()

        alertDialog?.setTitle("Hello")
        alertDialog?.setMessage("Error: " + e?.message)
        alertDialog?.setButton(AlertDialog.BUTTON_POSITIVE, "Ok"
        ) {


            dialog, which ->
            dialog.dismiss()
            recyclerView?.scrollToPosition(list?.size!! -5)
        }
        alertDialog?.show()
    }


    fun showRecyclerView() {
        recyclerView?.visibility = View.VISIBLE
    }

    fun hideRecyclerView() {
        recyclerView?.visibility = View.GONE
    }

    fun showLoadingDialog() {
        if (list == null) {
            list = ArrayList<Result>()
            list?.add(resultProgress)
            loadRecyclerViewData(list)
        } else {
            list?.add(resultProgress)
        }

        recyclerView?.adapter?.notifyDataSetChanged()

    }

    fun dismissLoadingDialog() {

        list?.remove(resultProgress)
        recyclerView?.adapter?.notifyDataSetChanged()
    }


    fun clearRecyclerView() {
        list?.clear()
        recyclerView?.adapter?.notifyDataSetChanged()
    }


}