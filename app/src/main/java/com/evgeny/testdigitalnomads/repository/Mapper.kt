package com.evgeny.testdigitalnomads.repository

import com.evgeny.testdigitalnomads.model.DBNews
import com.evgeny.testdigitalnomads.model.NetNews
import com.evgeny.testdigitalnomads.model.News


fun NetNews.toDBNews() = DBNews(
    date = date?.substring(0, 10) ?: "",
    imageUrl = imageUrl ?: "",
    title = title ?: "",
    description = description ?: "",
    url = url ?: ""
)

fun List<NetNews?>?.toListDBNews(): List<DBNews> {
    val completeList = mutableListOf<DBNews>()

    this?.forEach {
        val item = it?.toDBNews()
        item?.let {
            completeList.add(item)
        }
    }

    return completeList
}

