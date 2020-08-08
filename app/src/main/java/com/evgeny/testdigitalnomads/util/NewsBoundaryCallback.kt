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

class NewsBoundaryCallback() : PagedList.BoundaryCallback<DBNews>(), KoinComponent {

    private val repository by inject<Repository>()

    //==============================================================================================

    override fun onZeroItemsLoaded() {
        super.onZeroItemsLoaded()

    }

    override fun onItemAtEndLoaded(itemAtEnd: DBNews) {
        super.onItemAtEndLoaded(itemAtEnd)
        mlg("LOAD")

        GlobalScope.launch(Dispatchers.IO) {
            repository.getNews() { response ->
                when (response) {
                    is Resource.Success -> {}
                    is Resource.Error -> mlg(response.errorMessage.toString())
                    is Resource.Progress -> mlg(response.isLoading.toString())
                }
            }
        }
    }

}
