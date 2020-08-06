package com.evgeny.testdigitalnomads.mvvm.vm

import android.view.View
import androidx.databinding.ObservableField
import com.evgeny.testdigitalnomads.base.BaseVM
import com.evgeny.testdigitalnomads.model.News
import com.evgeny.testdigitalnomads.mvvm.view.NewsView
import com.evgeny.testdigitalnomads.ui.adapter.RVNewsAdapter
import com.evgeny.testdigitalnomads.util.DATE_PATTERN_NEWS_MAIN_DATE
import com.evgeny.testdigitalnomads.util.getCurrentDate
import com.evgeny.testdigitalnomads.util.mlg


class NewsVM : BaseVM(), NewsView {

    val newsAdapter = RVNewsAdapter(this)

    private val list = listOf(
        News(
            date = "06.08.2020",
            imageUrl = "https://w.wallhaven.cc/full/ym/wallhaven-ym1egl.jpg",
            title = "Lorem ipsum dolor sit amet, consectetur adipiscing elit",
            description = "Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."
        ),
        News(
            date = "06.08.2020",
            imageUrl = "https://w.wallhaven.cc/full/kw/wallhaven-kwy1w7.jpg",
            title = "Lorem ipsum dolor sit amet, consectetur adipiscing elit",
            description = "Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."
        ),
        News(
            date = "06.08.2020",
            imageUrl = "https://w.wallhaven.cc/full/zm/wallhaven-zmkzej.jpg",
            title = "Lorem ipsum dolor sit amet, consectetur adipiscing elit",
            description = "Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."
        ),
        News(
            date = "06.08.2020",
            imageUrl = "https://w.wallhaven.cc/full/dg/wallhaven-dg183o.jpg",
            title = "Lorem ipsum dolor sit amet, consectetur adipiscing elit",
            description = "Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."
        )
    )

    //==============================================================================================

    override val date: ObservableField<String> = ObservableField("")

    override fun btnOpenNews(view: View?, currentNews: News) {
        mlg("sdf")
    }

    init {
        setDate()
        setRvData()
    }

    //==============================================================================================

    private fun setDate() = launchOnViewModelScope {
        val currentDate = getCurrentDate(DATE_PATTERN_NEWS_MAIN_DATE)
        date.set(currentDate)
    }

    private fun setRvData() = launchOnViewModelScope {
        newsAdapter.list = list
    }


}