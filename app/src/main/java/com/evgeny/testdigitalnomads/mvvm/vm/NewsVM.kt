package com.evgeny.testdigitalnomads.mvvm.vm

import androidx.databinding.ObservableField
import com.evgeny.testdigitalnomads.base.BaseVM
import com.evgeny.testdigitalnomads.mvvm.view.NewsView
import com.evgeny.testdigitalnomads.util.DATE_PATTERN_NEWS_MAIN_DATE
import com.evgeny.testdigitalnomads.util.getCurrentDate


class NewsVM : BaseVM(), NewsView {

    //==============================================================================================

    override val date: ObservableField<String> = ObservableField("")

    init {
        setDate()
    }

    //==============================================================================================

    private fun setDate() = launchOnViewModelScope {
        val currentDate = getCurrentDate(DATE_PATTERN_NEWS_MAIN_DATE)
        date.set(currentDate)
    }


}