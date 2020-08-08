package com.evgeny.testdigitalnomads.util

import androidx.paging.PagedList
import com.evgeny.testdigitalnomads.model.DBNews

class NewsBoundaryCallback(private val listener: OnNewsBoundaryCallback) :
    PagedList.BoundaryCallback<DBNews>() {

    interface OnNewsBoundaryCallback {
        fun onZeroItemsLoaded()
        fun onItemAtEndLoaded()
    }

    //==============================================================================================

    override fun onZeroItemsLoaded() {
        super.onZeroItemsLoaded()

        listener.onZeroItemsLoaded()
    }

    override fun onItemAtEndLoaded(itemAtEnd: DBNews) {
        super.onItemAtEndLoaded(itemAtEnd)

        listener.onItemAtEndLoaded()
    }

}
