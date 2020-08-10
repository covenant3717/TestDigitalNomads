package com.evgeny.testdigitalnomads.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


data class News(
    var date: String = "",
    var imageUrl: String = "",
    var title: String = "",
    var description: String = "",
    var url: String = ""
)