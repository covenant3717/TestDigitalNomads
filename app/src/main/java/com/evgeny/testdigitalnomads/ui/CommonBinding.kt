package com.evgeny.testdigitalnomads.ui

import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.databinding.BindingAdapter
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import coil.transform.RoundedCornersTransformation
import com.evgeny.testdigitalnomads.R
import com.evgeny.testdigitalnomads.model.News
import com.evgeny.testdigitalnomads.ui.adapter.RVNewsAdapter
import com.evgeny.testdigitalnomads.util.invisible
import com.evgeny.testdigitalnomads.util.visible
import java.io.File


// COMMON ==========================================================================================
@BindingAdapter("toast")
fun bindToast(view: View, text: LiveData<String?>) {
    text.value?.let {
        Toast.makeText(view.context, it, Toast.LENGTH_SHORT).show()
    }
}

@BindingAdapter("progress")
fun bindProgress(view: View, status: LiveData<Boolean>) {
    status.value?.let {
        if (it) view.visible()
        else view.invisible()
    }
}

@BindingAdapter("viewVisibility")
fun bindViewVisibility(view: View, status: LiveData<Boolean>) {
    status.value?.let {
        if (it) view.visible()
        else view.invisible()
    }
}

@BindingAdapter("setAdapterRV")
fun setAdapterRV(rv: RecyclerView, adapter: RecyclerView.Adapter<*>) {
    rv.adapter = adapter
}


// CURRENT PROJECT =================================================================================

@BindingAdapter("setNewsImage")
fun setNewsImage(view: ImageView, imageUrl: String) {
    view.load(imageUrl) {
        crossfade(500)
        error(R.drawable.cmn_placeholder_transparent)
        transformations(RoundedCornersTransformation(24f))
    }
}

@BindingAdapter("setNewsList")
fun setNewsList(rv: RecyclerView, list: MutableLiveData<MutableList<News>>) {
    list.value?.let {
        (rv.adapter as? RVNewsAdapter)?.list  = it
    }
}