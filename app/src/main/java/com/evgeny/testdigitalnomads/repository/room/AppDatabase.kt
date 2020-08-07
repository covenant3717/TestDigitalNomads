package com.evgeny.testdigitalnomads.repository.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.evgeny.testdigitalnomads.model.DBNews


@Database(
    entities = [
        DBNews::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun mainDao(): MainDao

}

