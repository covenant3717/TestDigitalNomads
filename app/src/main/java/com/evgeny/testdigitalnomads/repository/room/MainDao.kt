package com.evgeny.testdigitalnomads.repository.room

import androidx.paging.DataSource
import androidx.room.*
import com.evgeny.testdigitalnomads.model.DBNews
import com.evgeny.testdigitalnomads.util.TABLE_NEWS

@Dao
interface MainDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(news: List<DBNews>)

    @Query("SELECT * FROM $TABLE_NEWS")
    fun getNews(): DataSource.Factory<Int, DBNews>

}