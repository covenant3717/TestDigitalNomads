package com.evgeny.testdigitalnomads.mvvm.view

import android.view.View
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import com.evgeny.testdigitalnomads.base.BaseView
import com.evgeny.testdigitalnomads.model.DBNews
import com.evgeny.testdigitalnomads.model.News


interface NewsView: BaseView {

    val date: ObservableField<String>
    val rvVsbl: MutableLiveData<Boolean>
    val tvRefreshVsbl: MutableLiveData<Boolean>
    fun btnOpenNews(view: View?, currentNews: DBNews)
    fun btnRefresh(view: View?)

}