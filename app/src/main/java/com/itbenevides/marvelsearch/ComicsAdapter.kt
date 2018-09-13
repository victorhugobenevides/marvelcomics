package com.itbenevides.marvelsearch

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.itbenevides.marvelsearch.model.`object`.ItemComics
import kotlinx.android.synthetic.main.marvel_item.view.*


class ComicsAdapter(private val list: List<ItemComics>?, private val context: Context) : Adapter<ComicsAdapter.ViewHolder>() {


    val ITEM_VIEW_TYPE_HEADER = 0
    val ITEM_VIEW_TYPE_FOOTER = 1

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val comics = list?.get(position)
        holder?.let {
            it.bindView(comics!!)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        var view: View? = null

        if (viewType == ITEM_VIEW_TYPE_HEADER) {
            view = LayoutInflater.from(context).inflate(R.layout.header_adapter, parent, false)


        } else if (viewType == ITEM_VIEW_TYPE_FOOTER) {
            view = LayoutInflater.from(context).inflate(R.layout.marvel_item, parent, false)


        }




        return ViewHolder(view, viewType)
    }

    override fun getItemCount(): Int {
        if (list != null) {
            return list.size
        }
        return 0
    }


    override fun getItemViewType(position: Int): Int {
        //check for the pre-defined value that will indicate footer
        return if (position == 0) ITEM_VIEW_TYPE_HEADER else com.itbenevides.marvelsearch.ITEM_VIEW_TYPE_FOOTER
    }


    class ViewHolder(private val it: View?, private val viewType: Int) : RecyclerView.ViewHolder(it) {

        fun bindView(comics: ItemComics) {


            if (viewType == ITEM_VIEW_TYPE_FOOTER) {
                val title = it?.title
                title?.text = comics.name
            } else if (viewType == ITEM_VIEW_TYPE_BASIC) {
                val title = it?.title
                title?.text = comics.name
            }

        }


    }

}




