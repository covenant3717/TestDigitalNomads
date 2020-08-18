package com.evgeny.testdigitalnomads.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.evgeny.testdigitalnomads.util.TABLE_NEWS
import com.google.gson.annotations.SerializedName


@Entity(tableName = TABLE_NEWS)
data class DBNews(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val date: String = "",
    val imageUrl: String = "",
    val title: String = "",
    val description: String = "",
    val url: String = ""
)