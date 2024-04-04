package com.tutorial.tvvideoapp

import android.os.Bundle
import android.view.View
import androidx.leanback.app.RowsSupportFragment
import androidx.leanback.widget.*


class ListFragment : RowsSupportFragment() {

    private var rootAdapter: ArrayObjectAdapter =
        ArrayObjectAdapter(ListRowPresenter(FocusHighlight.ZOOM_FACTOR_MEDIUM))

    private var itemClickHandler: ((details: Detail) -> Unit)? = null

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
                itemClickHandler?.let { it(item as Detail) }
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
        itemClickHandler = logic
    }

}