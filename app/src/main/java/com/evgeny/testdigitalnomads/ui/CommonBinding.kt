package com.evgeny.testdigitalnomads.ui

import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.databinding.BindingAdapter
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.evgeny.testdigitalnomads.R
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

@BindingAdapter("imageLink")
fun bindImageLink(view: ImageView, imageLink: ObservableField<String>) {
    view.load(imageLink.get()) {
        crossfade(true)
        error(R.drawable.cmn_placeholder_circle)
    }
}

@BindingAdapter("imageFile")
fun bindImageFile(view: ImageView, imgFile: ObservableField<File?>) {
    view.load(imgFile.get()) {
        crossfade(true)
        error(R.drawable.cmn_placeholder_circle)
    }
}

@BindingAdapter("setAdapterRV")
fun bindAdapterRV(rv: RecyclerView, adapter: RecyclerView.Adapter<*>) {
    rv.adapter = adapter
}


// CURRENT PROJECT =================================================================================

