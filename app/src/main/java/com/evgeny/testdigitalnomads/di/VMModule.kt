package com.evgeny.testdigitalnomads.di

import com.evgeny.testdigitalnomads.mvvm.vm.MainVM
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val VMModule = module {

    viewModel { MainVM() }
//    viewModel { LoginVM() }
//    viewModel { BidsVM() }

}