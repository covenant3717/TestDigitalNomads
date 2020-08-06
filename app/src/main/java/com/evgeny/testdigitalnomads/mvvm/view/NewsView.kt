package com.evgeny.testdigitalnomads.mvvm.view

import androidx.databinding.ObservableField
import com.evgeny.testdigitalnomads.base.BaseView


interface NewsView: BaseView {

    val date: ObservableField<String>

}