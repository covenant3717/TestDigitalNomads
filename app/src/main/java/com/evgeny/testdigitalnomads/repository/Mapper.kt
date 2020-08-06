package com.evgeny.testdigitalnomads.repository

import com.evgeny.testdigitalnomads.model.NetNews
import com.evgeny.testdigitalnomads.model.News


fun NetNews.toNews() = News(
    date = date?.substring(0, 10) ?: "",
    imageUrl = imageUrl ?: "",
    title = title ?: "",
    description = description ?: "",
    url = url ?: ""
)

fun List<NetNews?>?.toListNews(): List<News> {
    val completeList = mutableListOf<News>()

    this?.forEach {
        val item = it?.toNews()
        item?.let {
            completeList.add(item)
        }
    }

    return completeList
}

