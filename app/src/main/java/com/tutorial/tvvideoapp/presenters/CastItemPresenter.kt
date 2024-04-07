package com.tutorial.tvvideoapp.presenters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.leanback.widget.Presenter
import com.bumptech.glide.Glide
import com.tutorial.tvvideoapp.R
import com.tutorial.tvvideoapp.models.cast.Cast
import com.tutorial.tvvideoapp.utils.UtilFunctions

class CastItemPresenter: Presenter() {
    override fun onCreateViewHolder(parent: ViewGroup?): ViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.cast_item_view,parent,false)
        view.layoutParams.width = UtilFunctions.getWidthInPercent(parent!!.context,20)
        view.layoutParams.height = UtilFunctions.getHeightInPercent(parent.context, 35)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder?, item: Any?) {
        val content = item as? Cast
        val imageView = viewHolder?.view?.findViewById<ImageView>(R.id.iv_cast)
        val actorName = viewHolder?.view?.findViewById<TextView>(R.id.tv_actor_name)
        val charDeptName = viewHolder?.view?.findViewById<TextView>(R.id.tv_character_dept)

        Glide.with(viewHolder?.view?.context!!).load(UtilFunctions.getImageUrl(content?.profile_path!!)).into(imageView!!)
        actorName?.text = content.name
        charDeptName?.text = content.character
    }

    override fun onUnbindViewHolder(viewHolder: ViewHolder?) {

    }
}