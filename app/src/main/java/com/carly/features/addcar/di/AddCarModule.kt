package com.carly.features.addcar.di

import com.carly.features.addcar.domain.FetchStepDataUseCaseUseCase
import com.carly.features.addcar.rp.AddCarRepository
import com.carly.features.addcar.rp.AddCarRepositoryImpl
import com.carly.features.addcar.vm.AddCarViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val addCarModule = module {
    single<AddCarRepository> { AddCarRepositoryImpl(get(), get()) }
    single { FetchStepDataUseCaseUseCase(get()) }
    viewModel { AddCarViewModel(get()) }
}