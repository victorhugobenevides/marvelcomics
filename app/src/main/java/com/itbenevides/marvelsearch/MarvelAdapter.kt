package com.itbenevides.marvelsearch

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.Adapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.itbenevides.marvelsearch.model.`object`.Result
import kotlinx.android.synthetic.main.load_more.view.*
import kotlinx.android.synthetic.main.marvel_item.view.*


val ITEM_VIEW_TYPE_BASIC = 0
val ITEM_VIEW_TYPE_FOOTER = 1


class MarvelAdapter(private val list: List<Result>?,
                    private val context: Context, private val onClickInterface: OnClickInterface) : Adapter<MarvelAdapter.ViewHolder>() {


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = list?.get(position)
        holder?.let {
            it.bindView(movie!!, context, onClickInterface)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        var view: View? = null

        if (viewType == ITEM_VIEW_TYPE_BASIC) {
            view = LayoutInflater.from(context).inflate(R.layout.marvel_item, parent, false)


        } else if (viewType == ITEM_VIEW_TYPE_FOOTER) {
            view = LayoutInflater.from(context).inflate(R.layout.load_more, parent, false)


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
        return if (list?.get(position)?.id != null) ITEM_VIEW_TYPE_BASIC else ITEM_VIEW_TYPE_FOOTER
    }


    class ViewHolder(private val it: View?, private val viewType: Int) : RecyclerView.ViewHolder(it) {

        fun bindView(marvel: Result, context: Context, onClickInterface: OnClickInterface) {

            if (viewType == ITEM_VIEW_TYPE_FOOTER) {
                val progressBar = it?.progress_bar
                progressBar?.visibility = View.VISIBLE
            } else if (viewType == ITEM_VIEW_TYPE_BASIC) {
                val title = it?.title
                val image = it?.imageView
                title?.text = marvel.name


                Glide.with(context).load(marvel.thumbnail?.path + "." + marvel.thumbnail?.extension).apply(RequestOptions.circleCropTransform()).into(image!!)
                itemView.setOnClickListener {
                    onClickInterface.onClick(itemView, marvel)
                }
            }


        }

    }


}