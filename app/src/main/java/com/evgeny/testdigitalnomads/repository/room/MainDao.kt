package com.evgeny.testdigitalnomads.repository.room

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.evgeny.testdigitalnomads.model.DBNews
import com.evgeny.testdigitalnomads.util.TABLE_NEWS

@Dao
interface MainDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(news: List<DBNews>)

    @Query("DELETE FROM $TABLE_NEWS")
    fun clearNews()

    @Query("SELECT * FROM $TABLE_NEWS")
    fun getNews(): DataSource.Factory<Int, DBNews>

}