package com.evgeny.testdigitalnomads.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


//@Parcelize
//data class Bid(
//    var bidId: String = "",
//    var bidType: String = "",
//    var number: String = "",
//    var name: String = "",
//    var address: String = "",
//    var info: String = "",
//    var color: String = "",
//    var icon: String = "",
//    var masterPhone: String = "",
//    var newComments: Int = 0,
//    var sort: String = ""
//) : Parcelable

data class News(
    var date: String = "",
    var imageUrl: String = "",
    var title: String = "",
    var description: String = "",
    var url: String = ""
)