package com.itbenevides.marvelsearch

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.MenuItem
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.RequestOptions.bitmapTransform
import com.itbenevides.marvelsearch.model.`object`.ItemComics
import com.itbenevides.marvelsearch.model.`object`.Result
import com.itbenevides.marvelsearch.view.ViewDetail
import jp.wasabeef.glide.transformations.BlurTransformation
import kotlinx.android.synthetic.main.activity_detail.*


class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        ViewDetail(this,intent.extras["char"] as Result)

    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.getItemId()){
            android.R.id.home -> finish()
        }

        return super.onOptionsItemSelected(item)
    }

}
