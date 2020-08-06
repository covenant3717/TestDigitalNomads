package com.evgeny.testdigitalnomads.mvvm.view

import android.view.View
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import com.evgeny.testdigitalnomads.base.BaseView
import com.evgeny.testdigitalnomads.model.News


interface NewsView: BaseView {

    val date: ObservableField<String>
    val newsList: MutableLiveData<List<News>>
    fun btnOpenNews(view: View?, currentNews: News)

}