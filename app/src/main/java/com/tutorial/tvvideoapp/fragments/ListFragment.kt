package com.tutorial.tvvideoapp.fragments

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.leanback.app.RowsSupportFragment
import androidx.leanback.widget.*
import com.tutorial.tvvideoapp.DetailsActivity
import com.tutorial.tvvideoapp.presenters.ItemPresenter
import com.tutorial.tvvideoapp.models.Detail
import com.tutorial.tvvideoapp.models.MoviesDataModel
import com.tutorial.tvvideoapp.models.cast.MovieCastDataModel
import com.tutorial.tvvideoapp.presenters.CastItemPresenter


class ListFragment : RowsSupportFragment() {

    // lRP with no shadows
    private var listRowPresenter = object:ListRowPresenter(FocusHighlight.ZOOM_FACTOR_MEDIUM){
        override fun isUsingDefaultListSelectEffect(): Boolean {
            return false
        }
    }.apply { shadowEnabled = false }

    private var rootAdapter: ArrayObjectAdapter =
        ArrayObjectAdapter(listRowPresenter)

    private var itemSelectHandler: ((details: Detail) -> Unit)? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = rootAdapter

        onItemViewClickedListener =
            OnItemViewClickedListener { _, item, _, _ ->
                if(item is Detail){
                    startActivity(Intent(requireContext(),DetailsActivity::class.java)
                        .putExtra("id",item.id))
                }
            }

        onItemViewSelectedListener =
            OnItemViewSelectedListener { _, item, _, _ ->
                if(item is Detail){
                    itemSelectHandler?.invoke(item)
                }
            }

    }

    fun bindData(dataList: MoviesDataModel) {

        dataList.result.forEachIndexed { index, result ->
            val arrayObjectAdapter = ArrayObjectAdapter(ItemPresenter())

            result.details.forEach {
                arrayObjectAdapter.add(it)
            }

            val headerItem = HeaderItem(result.title)
            val listRow = ListRow(headerItem, arrayObjectAdapter)
            rootAdapter.add(listRow)

        }

    }

    fun clickLogicSetter(logic: ((details: Detail) -> Unit)?){
        itemSelectHandler = logic
    }

    fun bindCastData(creditDetails: MovieCastDataModel) {
        // including only the cast and not the crew for now
        val arrayObjectAdapter = ArrayObjectAdapter(CastItemPresenter())
        creditDetails.cast.subList(0, 10).forEach{
            arrayObjectAdapter.add(it)
        }
        val headerItem = HeaderItem("Cast")
        val listRow = ListRow(headerItem, arrayObjectAdapter)
        rootAdapter.add(listRow)
    }

}