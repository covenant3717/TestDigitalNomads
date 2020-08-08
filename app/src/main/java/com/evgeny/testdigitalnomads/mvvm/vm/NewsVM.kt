package com.evgeny.testdigitalnomads.mvvm.vm

import android.view.View
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.evgeny.testdigitalnomads.R
import com.evgeny.testdigitalnomads.base.BaseVM
import com.evgeny.testdigitalnomads.model.DBNews
import com.evgeny.testdigitalnomads.mvvm.view.NewsView
import com.evgeny.testdigitalnomads.ui.activity.WebViewActivity
import com.evgeny.testdigitalnomads.ui.adapter.RVNewsAdapter
import com.evgeny.testdigitalnomads.util.*
import kotlinx.android.synthetic.main.activity_news.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.vippolis.employeecontrol.repository.Resource


class NewsVM : BaseVM(), NewsView, NewsBoundaryCallback.OnNewsBoundaryCallback {

    private var NEWS_PAGE = 1
    var newsAdapter = RVNewsAdapter(this)

    //==============================================================================================

    override val date: ObservableField<String> =
        ObservableField(getCurrentDate(DATE_PATTERN_NEWS_MAIN_DATE))
    override val newsPagedList: MutableLiveData<PagedList<DBNews>?>? = MutableLiveData(null)
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
        initNewsPagedListListener()
    }

    override fun onZeroItemsLoaded() {
        getNews()
    }

    override fun onItemAtEndLoaded() {
        getNews()
    }

    //==============================================================================================

    private fun initNewsPagedListListener() = launchOnViewModelScope {
        progress.postValue(true)
        initPagedNewsList().observeForever(Observer {
            if (it.isNotEmpty()){
                newsPagedList?.postValue(it)

                rvVsbl.postValue(true)
                progress.postValue(false)
                tvRefreshVsbl.postValue(false)
            }
        })
    }

    private fun getNews() = launchOnViewModelScope {
        if (NEWS_PAGE <= 5) {
            repository.getNews(NEWS_PAGE) { response ->
                when (response) {
                    is Resource.Success -> {
                        NEWS_PAGE += 1
                    }
                    is Resource.Error -> {
                        toast.postValue(response.errorMessage)
                        tvRefreshVsbl.postValue(true)
                        rvVsbl.postValue(false)
                        progress.postValue(false)
                    }
                    is Resource.Progress -> {
//                        progress.postValue(response.isLoading)
                    }
                }
            }
        }
    }

    private fun initPagedNewsList(): LiveData<PagedList<DBNews>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(5)
            .setPrefetchDistance(5)
            .build()

        val pagedListBuilder: LivePagedListBuilder<Int, DBNews> =
            LivePagedListBuilder<Int, DBNews>(getNewsDataSource(), config)

        pagedListBuilder.setBoundaryCallback(NewsBoundaryCallback(this))

        return pagedListBuilder.build()
    }

    private fun getNewsDataSource(): DataSource.Factory<Int, DBNews> {
        return repository.getNewsDataSource()
    }


}