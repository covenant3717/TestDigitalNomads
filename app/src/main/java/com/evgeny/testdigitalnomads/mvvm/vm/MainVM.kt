package com.evgeny.testdigitalnomads.mvvm.vm

import com.evgeny.testdigitalnomads.base.BaseVM
import com.evgeny.testdigitalnomads.mvvm.view.MainView
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
        }
    }

    private fun clearCache() = launchOnViewModelScope {
        repository.clearCache()
    }

    private fun gotToNews() = launchOnViewModelScope {
        delay(2000)
        startActivity.postValue(TAG_ACTIVITY_NEWS)
    }


}