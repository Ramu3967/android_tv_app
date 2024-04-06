package com.tutorial.tvvideoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import com.bumptech.glide.Glide

class DetailsActivity : FragmentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        Glide.with(this).load("https://image.tmdb.org/t/p/w500/jBIMZ0AlUYuFNsKbd4L6FojWMoy.jpg").into(
        findViewById<ImageView>(R.id.iv_details_banner))

        findViewById<TextView>(R.id.tv_details_play).setOnClickListener { Toast.makeText(this,"clicked play",Toast.LENGTH_SHORT).show() }
    }
}