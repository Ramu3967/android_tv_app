package com.tutorial.tvvideoapp

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.tutorial.tvvideoapp.models.Detail
import com.tutorial.tvvideoapp.models.MoviesDataModel
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader

class MainActivity : FragmentActivity() {

    lateinit var txtTitle: TextView
    lateinit var txtSubTitle: TextView
    lateinit var txtDescription: TextView

    lateinit var imgBanner: ImageView
    lateinit var listFragment: ListFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        imgBanner = findViewById(R.id.img_banner)
        txtTitle = findViewById(R.id.title)
        txtSubTitle = findViewById(R.id.subtitle)
        txtDescription = findViewById(R.id.description)

        val listFragment = ListFragment()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.list_fragment, listFragment)
        transaction.commit()


        val gson = Gson()
        val i: InputStream = this.assets.open("movies.json")
        val br = BufferedReader(InputStreamReader(i))
        val dataList = gson.fromJson(br, MoviesDataModel::class.java)

        listFragment.bindData(dataList)
        listFragment.clickLogicSetter(logic = { updateBanner(it) })

    }

    fun updateBanner(movieDetails: Detail){
        txtTitle.text = movieDetails.original_title
        txtSubTitle.text = "Language: ${movieDetails.original_language}"
        txtDescription.text = movieDetails.overview
        val url = "https://www.themoviedb.org/t/p/w500" + movieDetails.backdrop_path
        Glide.with(this).load(url).into(imgBanner)
    }
}