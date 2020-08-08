package com.evgeny.testdigitalnomads.mvvm.vm

import android.view.View
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import com.evgeny.testdigitalnomads.base.BaseVM
import com.evgeny.testdigitalnomads.model.DBNews
import com.evgeny.testdigitalnomads.model.News
import com.evgeny.testdigitalnomads.mvvm.view.NewsView
import com.evgeny.testdigitalnomads.repository.toListNews
import com.evgeny.testdigitalnomads.ui.activity.WebViewActivity
import com.evgeny.testdigitalnomads.ui.adapter.RVNewsAdapter
import com.evgeny.testdigitalnomads.util.*
import ru.vippolis.employeecontrol.repository.Resource


class NewsVM : BaseVM(), NewsView {

    //==============================================================================================

    override val date: ObservableField<String> = ObservableField("")

    override fun btnOpenNews(view: View?, currentNews: DBNews) {
        view?.context?.launchActivity<WebViewActivity> {
            putExtra(POST_URL, currentNews.url)
        }
    }

    init {
        setDate()
    }

    //==============================================================================================

    private fun setDate() = launchOnViewModelScope {
        val currentDate = getCurrentDate(DATE_PATTERN_NEWS_MAIN_DATE)
        date.set(currentDate)
    }

    suspend fun getPagedNewsList(): LiveData<PagedList<DBNews>> {
        return repository.getPagedNewsList()
    }


}