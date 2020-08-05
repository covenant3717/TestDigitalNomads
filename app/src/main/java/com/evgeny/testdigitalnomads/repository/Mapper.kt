package com.evgeny.testdigitalnomads.repository

import com.evgeny.testdigitalnomads.model.Bid



/*
fun NetBid.toBid() = Bid(
    bidId = bidId ?: "",
    bidType = bidType ?: "",
    number = bidNumber ?: "",
    name = bidName ?: "",
    address = bidAddress ?: "",
    info = bidInfo ?: "",
    color = bidColor ?: "",
    icon = bidIcon ?: "",
    masterPhone = bidMasterPhone ?: "",
    newComments = bidNewComments ?: 0,
    sort = bidSort ?: ""
)

fun List<NetBid?>?.toBidList(): List<Bid> {
    val completeList = mutableListOf<Bid>()

    this?.forEach {
        val item = it?.toBid()
        item?.let {
            if (item.bidId.isNotEmpty()) {
                completeList.add(item)
            }
        }
    }

    return completeList
}

*/
