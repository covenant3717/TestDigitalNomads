package com.evgeny.testdigitalnomads.mvvm.vm

import android.view.View
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import com.evgeny.testdigitalnomads.R
import com.evgeny.testdigitalnomads.base.BaseVM
import com.evgeny.testdigitalnomads.model.DBNews
import com.evgeny.testdigitalnomads.mvvm.view.NewsView
import com.evgeny.testdigitalnomads.ui.activity.WebViewActivity
import com.evgeny.testdigitalnomads.util.*
import ru.vippolis.employeecontrol.repository.Resource


class NewsVM : BaseVM(), NewsView {

    //==============================================================================================

    override val date: ObservableField<String> = ObservableField("")
    override val rvVsbl: MutableLiveData<Boolean> = MutableLiveData(false)
    override val tvRefreshVsbl: MutableLiveData<Boolean> = MutableLiveData(false)

    override fun btnOpenNews(view: View?, currentNews: DBNews) {
        view?.context?.launchActivity<WebViewActivity> {
            putExtra(POST_URL, currentNews.url)
        }
    }

    override fun btnRefresh(view: View?) {
        if (isConnected()) getNews()
        if (!isConnected()) toast.postValue(getStringRes(R.string.internet_disconnected))
    }

    init {
        setDate()
    }

    //==============================================================================================

    private fun setDate() = launchOnViewModelScope {
        val currentDate = getCurrentDate(DATE_PATTERN_NEWS_MAIN_DATE)
        date.set(currentDate)
    }

    private fun getNews() = launchOnViewModelScope {
        repository.getNews() { response ->
            when (response) {
                is Resource.Success -> {
                    progress.postValue(false)
                    tvRefreshVsbl.postValue(false)
                    rvVsbl.postValue(true)
                }
                is Resource.Error -> toast.postValue(response.errorMessage)
                is Resource.Progress -> progress.postValue(response.isLoading)
            }
        }
    }

    suspend fun getPagedNewsList(): LiveData<PagedList<DBNews>> {
        return repository.getPagedNewsList()
    }


}