package com.evgeny.testdigitalnomads.di

import com.evgeny.testdigitalnomads.repository.Repository
import com.evgeny.testdigitalnomads.repository.network.MainApiClient
import com.evgeny.testdigitalnomads.repository.room.MainDao
import org.koin.dsl.module


val RepositoryModule = module {

    // Repository
    single {
        Repository(get<MainApiClient>(), get<MainDao>())
    }
}