package com.evgeny.testdigitalnomads.base

import androidx.lifecycle.MutableLiveData

interface BaseView {
    val toast: MutableLiveData<String?>
    val progress: MutableLiveData<Boolean?>
    val startActivity: MutableLiveData<String?>
}