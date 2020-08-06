package com.evgeny.testdigitalnomads.mvvm.vm

import com.evgeny.testdigitalnomads.util.TAG_ACTIVITY_NEWS
import com.evgeny.testdigitalnomads.base.BaseVM
import com.evgeny.testdigitalnomads.mvvm.view.MainView
import kotlinx.coroutines.delay


class MainVM : BaseVM(), MainView {

    //==============================================================================================

    init {
        gotToNews()
    }

    //==============================================================================================

    private fun gotToNews() = launchOnViewModelScope {
        delay(2000)
        startActivity.postValue(TAG_ACTIVITY_NEWS)
    }


}