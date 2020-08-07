package com.evgeny.testdigitalnomads.di

import com.evgeny.testdigitalnomads.repository.network.MainApiClient
import org.koin.dsl.module
import com.evgeny.testdigitalnomads.repository.Repository
import com.evgeny.testdigitalnomads.repository.room.MainDao


val RepositoryModule = module {

    // Repository
    single {
        Repository(get<MainApiClient>(), get<MainDao>())
//        Repository(get<MainApiClient>())
    }
}