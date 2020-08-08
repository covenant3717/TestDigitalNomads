package com.evgeny.testdigitalnomads.util

import androidx.paging.PagedList
import com.evgeny.testdigitalnomads.model.DBNews
import com.evgeny.testdigitalnomads.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject
import ru.vippolis.employeecontrol.repository.Resource

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
