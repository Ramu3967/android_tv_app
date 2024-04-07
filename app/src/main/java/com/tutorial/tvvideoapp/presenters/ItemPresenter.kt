package com.tutorial.tvvideoapp.presenters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.leanback.widget.Presenter
import com.bumptech.glide.Glide
import com.tutorial.tvvideoapp.R
import com.tutorial.tvvideoapp.models.Detail
import com.tutorial.tvvideoapp.utils.UtilFunctions.getHeightInPercent
import com.tutorial.tvvideoapp.utils.UtilFunctions.getWidthInPercent

class ItemPresenter : Presenter() {
    override fun onCreateViewHolder(parent: ViewGroup?): ViewHolder {

        val view =
            LayoutInflater.from(parent?.context).inflate(R.layout.item_view, parent, false)

        val params = view.layoutParams
        params.width = getWidthInPercent(parent!!.context, 12)
        params.height = getHeightInPercent(parent!!.context, 32)

        return ViewHolder(view)

    }


    override fun onBindViewHolder(viewHolder: ViewHolder?, item: Any?) {

        val content = item as? Detail

        val imageview = viewHolder?.view?.findViewById<ImageView>(R.id.poster_image)

        val url = "https://www.themoviedb.org/t/p/w500" + content?.poster_path
        Glide.with(viewHolder?.view?.context!!)
            .load(url)
            .into(imageview!!)

    }

    override fun onUnbindViewHolder(viewHolder: ViewHolder?) {
    }
}