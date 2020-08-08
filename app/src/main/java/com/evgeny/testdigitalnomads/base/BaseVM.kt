package com.evgeny.testdigitalnomads.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject
import com.evgeny.testdigitalnomads.repository.Repository

abstract class BaseVM() : ViewModel(), BaseView, KoinComponent {

    protected val repository by inject<Repository>()

    //==============================================================================================

    override val toast: MutableLiveData<String?> = MutableLiveData()
    override val progress: MutableLiveData<Boolean?> = MutableLiveData(false)
    override val startActivity: MutableLiveData<String?> = MutableLiveData()

    override fun onCleared() {
        super.onCleared()

        clear()
    }

    //==============================================================================================

    fun launchOnViewModelScope(block: suspend () -> Unit): Job {
        return viewModelScope.launch(Dispatchers.Main) {
            block()
        }
    }

    fun clear() {
        toast.postValue(null)
        progress.postValue(false)
        startActivity.postValue(null)
    }

}