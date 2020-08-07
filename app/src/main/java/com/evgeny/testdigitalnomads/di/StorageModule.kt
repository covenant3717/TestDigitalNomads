package com.evgeny.testdigitalnomads.di

import androidx.room.Room
import com.evgeny.testdigitalnomads.repository.room.AppDatabase
import com.evgeny.testdigitalnomads.util.DB_NAME
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module


val StorageModule = module {

    // Room
    single { Room.databaseBuilder(androidApplication(), AppDatabase::class.java, DB_NAME).build() }

    // MainDao
    single { get<AppDatabase>().mainDao() }

}