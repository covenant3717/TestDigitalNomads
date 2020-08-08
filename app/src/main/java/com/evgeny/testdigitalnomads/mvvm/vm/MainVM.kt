package com.evgeny.testdigitalnomads.mvvm.vm

import com.evgeny.testdigitalnomads.util.TAG_ACTIVITY_NEWS
import com.evgeny.testdigitalnomads.base.BaseVM
import com.evgeny.testdigitalnomads.mvvm.view.MainView
import com.evgeny.testdigitalnomads.util.NewsBoundaryCallback.Companion.NEWS_PAGE
import com.evgeny.testdigitalnomads.util.isConnected
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.vippolis.employeecontrol.repository.Resource


class MainVM : BaseVM(), MainView {

    //==============================================================================================

    init {
        checkInternet()
        gotToNews()
    }

    //==============================================================================================

    private fun checkInternet() = launchOnViewModelScope {
        if (isConnected()) {
            clearCache()

            repository.getNews(NEWS_PAGE) { response ->
                when (response) {
                    is Resource.Success -> {
                        GlobalScope.launch(Dispatchers.IO) {
                            repository.saveNews(response.value)
                            NEWS_PAGE += 1
                        }
                    }
                    is Resource.Error -> toast.postValue(response.errorMessage)
                    is Resource.Progress -> progress.postValue(response.isLoading)
                }
            }
        }
    }

    private fun clearCache() = launchOnViewModelScope {
        repository.clearNews()
        NEWS_PAGE = 1
    }

    private fun gotToNews() = launchOnViewModelScope {
        delay(2000)
        startActivity.postValue(TAG_ACTIVITY_NEWS)
    }


}