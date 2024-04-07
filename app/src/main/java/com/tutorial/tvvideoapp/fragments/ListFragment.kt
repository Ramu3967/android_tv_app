package com.tutorial.tvvideoapp.fragments

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.leanback.app.RowsSupportFragment
import androidx.leanback.widget.*
import com.tutorial.tvvideoapp.DetailsActivity
import com.tutorial.tvvideoapp.ItemPresenter
import com.tutorial.tvvideoapp.models.Detail
import com.tutorial.tvvideoapp.models.MoviesDataModel
import com.tutorial.tvvideoapp.models.moviedetails.MovieDetailsDataModel


class ListFragment : RowsSupportFragment() {

    private var rootAdapter: ArrayObjectAdapter =
        ArrayObjectAdapter(ListRowPresenter(FocusHighlight.ZOOM_FACTOR_MEDIUM))

    private var itemSelectHandler: ((details: Detail) -> Unit)? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = rootAdapter

        onItemViewClickedListener = object: OnItemViewClickedListener{
            override fun onItemClicked(
                itemViewHolder: Presenter.ViewHolder?,
                item: Any?,
                rowViewHolder: RowPresenter.ViewHolder?,
                row: Row?
            ) {
                if(item is Detail){
                    startActivity(Intent(requireContext(),DetailsActivity::class.java)
                        .putExtra("id",item.id))
                }

            }
        }

        onItemViewSelectedListener = object: OnItemViewSelectedListener{
            override fun onItemSelected(
                itemViewHolder: Presenter.ViewHolder?,
                item: Any?,
                rowViewHolder: RowPresenter.ViewHolder?,
                row: Row?
            ) {
                if(item is Detail){
                    itemSelectHandler?.invoke(item)
                }
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

    fun bindCastData(movieDetails: MovieDetailsDataModel) {

    }

}