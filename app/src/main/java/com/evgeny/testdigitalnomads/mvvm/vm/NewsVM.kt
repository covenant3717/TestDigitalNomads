package com.evgeny.testdigitalnomads.mvvm.vm

import android.view.View
import android.widget.Toast
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.evgeny.testdigitalnomads.base.BaseVM
import com.evgeny.testdigitalnomads.model.DBNews
import com.evgeny.testdigitalnomads.mvvm.view.NewsView
import com.evgeny.testdigitalnomads.ui.activity.WebViewActivity
import com.evgeny.testdigitalnomads.util.*
import com.evgeny.testdigitalnomads.repository.network.NewsPagingSource
import com.evgeny.testdigitalnomads.ui.adapter.RVNewsAdapter
import kotlinx.coroutines.flow.collectLatest


class NewsVM : BaseVM(), NewsView {

    var newsAdapter = RVNewsAdapter(this)

    private val newsFlow = Pager(PagingConfig(pageSize = 20, prefetchDistance = 5)) {
        NewsPagingSource(mainApi)
    }.flow
        .cachedIn(viewModelScope)

    //==============================================================================================

    override val date: ObservableField<String> =
        ObservableField(getCurrentDate(DATE_PATTERN_NEWS_MAIN_DATE))
    override val tvRefreshVsbl: MutableLiveData<Boolean> = MutableLiveData(false)

    override fun btnOpenNews(view: View?, currentNews: DBNews) {
        view?.context?.launchActivity<WebViewActivity> {
            putExtra(POST_URL, currentNews.url)
        }
    }

    override fun btnRefresh(view: View?) {
    }

    init {
        initNewsPagedListListener()
        initLoadStateListener()
    }

    //==============================================================================================


    private fun initNewsPagedListListener() = launchOnViewModelScope {
        newsFlow.collectLatest { pagingData ->
            newsAdapter.submitData(pagingData)
        }
    }

    private fun initLoadStateListener() {
        newsAdapter.addLoadStateListener { loadState ->
            if (loadState.refresh is LoadState.Loading) {
                progress.postValue(true)
            } else {
                progress.postValue(false)

                // getting the error
                val error = when {
                    loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
                    loadState.append is LoadState.Error -> loadState.append as LoadState.Error
                    loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
                    else -> null
                }
                error?.let {
                    toast.postValue(it.error.message.toString())
                }
            }
        }
    }

}