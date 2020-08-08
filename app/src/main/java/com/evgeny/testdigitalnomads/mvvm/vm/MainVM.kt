package com.evgeny.testdigitalnomads.mvvm.vm

import com.evgeny.testdigitalnomads.base.BaseVM
import com.evgeny.testdigitalnomads.mvvm.view.MainView
import com.evgeny.testdigitalnomads.repository.Repository.Companion.NEWS_PAGE
import com.evgeny.testdigitalnomads.util.TAG_ACTIVITY_NEWS
import com.evgeny.testdigitalnomads.util.isConnected
import kotlinx.coroutines.delay


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

            repository.getNews() { response -> }
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