package com.evgeny.testdigitalnomads.mvvm.vm

import android.view.View
import androidx.databinding.ObservableField
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.evgeny.testdigitalnomads.base.BaseVM
import com.evgeny.testdigitalnomads.model.DBNews
import com.evgeny.testdigitalnomads.mvvm.view.NewsView
import com.evgeny.testdigitalnomads.ui.activity.WebViewActivity
import com.evgeny.testdigitalnomads.util.DATE_PATTERN_NEWS_MAIN_DATE
import com.evgeny.testdigitalnomads.util.POST_URL
import com.evgeny.testdigitalnomads.util.getCurrentDate
import com.evgeny.testdigitalnomads.util.launchActivity
import kotlinx.coroutines.flow.Flow


class NewsVM : BaseVM(), NewsView {


    //==============================================================================================

    override val date: ObservableField<String> =
        ObservableField(getCurrentDate(DATE_PATTERN_NEWS_MAIN_DATE))

    override fun btnOpenNews(view: View?, currentNews: DBNews) {
        view?.context?.launchActivity<WebViewActivity> {
            putExtra(POST_URL, currentNews.url)
        }
    }

    //==============================================================================================

    suspend fun getNewsFlow(): Flow<PagingData<DBNews>> {
        return repository.getNewsFlow()
            .cachedIn(viewModelScope)
    }


}