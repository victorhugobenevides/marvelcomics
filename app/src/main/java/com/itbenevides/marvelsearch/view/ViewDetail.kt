package com.itbenevides.marvelsearch.view

import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.itbenevides.marvelsearch.ComicsAdapter
import com.itbenevides.marvelsearch.DetailActivity
import com.itbenevides.marvelsearch.R
import com.itbenevides.marvelsearch.model.`object`.ItemComics
import com.itbenevides.marvelsearch.model.`object`.Result
import jp.wasabeef.glide.transformations.BlurTransformation
import kotlinx.android.synthetic.main.activity_detail.*


class ViewDetail(private val activity: DetailActivity, private val result: Result)  {

    var title: TextView? = null
    var description: TextView? = null
    var recyclerView: RecyclerView? = null
    var imageView: ImageView? = null
    var imageView_bkg: ImageView? = null
    var toolbar: Toolbar? = null


    init {

        activity.setContentView(R.layout.activity_detail)

        title = activity.textView_title
        description = activity.textView_description
        recyclerView = activity.heroes_recycler
        imageView = activity.imageView
        imageView_bkg = activity.imageView_bkg




        activity.supportActionBar?.setDisplayShowHomeEnabled(true)
        activity.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        activity.supportActionBar?.title = ""



        load()

    }


    fun load() {

        title?.text = result.name

        if (!result.description.equals(""))
            description?.text = "Description: " + result.description

        Glide.with(activity).load(result.thumbnail?.path + "." + result.thumbnail?.extension).thumbnail(0.7f).apply(RequestOptions.circleCropTransform()).into(imageView!!)
        Glide.with(activity).load(result.thumbnail?.path + "." + result.thumbnail?.extension).thumbnail(0.7f).apply(RequestOptions.bitmapTransform(BlurTransformation(25, 3))).into(imageView_bkg!!)






            val ItemComics = ItemComics()
            ItemComics.name = "Comics"
            result.comics?.items?.add(0,ItemComics)


        recyclerView?.adapter = ComicsAdapter(result.comics?.items, activity)
        recyclerView?.layoutManager = LinearLayoutManager(activity)



    }

}