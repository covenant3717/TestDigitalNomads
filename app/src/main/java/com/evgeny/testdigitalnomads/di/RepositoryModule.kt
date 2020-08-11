package com.evgeny.testdigitalnomads.di

import com.evgeny.testdigitalnomads.repository.network.MainApiClient
import org.koin.dsl.module
import com.evgeny.testdigitalnomads.repository.Repository
import com.evgeny.testdigitalnomads.repository.room.MainDao


val RepositoryModule = module {

    single {
        Repository(get(), get(), get())
    }
}