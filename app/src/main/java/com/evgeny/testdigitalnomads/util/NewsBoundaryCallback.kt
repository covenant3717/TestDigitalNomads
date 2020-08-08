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

    companion object {
        var NEWS_PAGE = 1
    }

    private val repository by inject<Repository>()


    //==============================================================================================

    override fun onZeroItemsLoaded() {
        super.onZeroItemsLoaded()
/*
        mlg("FIRST LOAD")

        GlobalScope.launch(Dispatchers.IO) {
            repository.getNews(NEWS_PAGE) { response ->
                when (response) {
                    is Resource.Success -> {
                        GlobalScope.launch(Dispatchers.IO) {
                            repository.saveNews(response.value)
                        }
                    }
                    is Resource.Error -> mlg(response.errorMessage.toString())
                    is Resource.Progress -> mlg(response.isLoading.toString())
                }
            }
        }
*/
    }

    override fun onItemAtEndLoaded(itemAtEnd: DBNews) {
        super.onItemAtEndLoaded(itemAtEnd)
        mlg("LOAD")

        if (NEWS_PAGE <= 5) {
            GlobalScope.launch(Dispatchers.IO) {
                repository.getNews(NEWS_PAGE) { response ->
                    when (response) {
                        is Resource.Success -> {
                            GlobalScope.launch(Dispatchers.IO) {
                                repository.saveNews(response.value)
                                NEWS_PAGE += 1
                            }
                        }
                        is Resource.Error -> mlg(response.errorMessage.toString())
                        is Resource.Progress -> mlg(response.isLoading.toString())
                    }
                }
            }
        }
    }
}
