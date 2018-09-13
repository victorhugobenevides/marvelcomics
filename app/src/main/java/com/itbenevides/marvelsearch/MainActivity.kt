package com.itbenevides.marvelsearch

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.itbenevides.marvelsearch.view.ViewMain


class MainActivity : AppCompatActivity() {

    var viewMain:ViewMain?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewMain=ViewMain(this)




    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {

        menuInflater.inflate(R.menu.main_menu, menu)

        viewMain?.loadMenu(menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        when (item?.getItemId()) {

            R.id.refresh -> viewMain?.OnClickrefrashButton()

        }


        return super.onOptionsItemSelected(item)
    }



}
