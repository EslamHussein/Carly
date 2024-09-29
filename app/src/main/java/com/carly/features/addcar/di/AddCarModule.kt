package com.carly.features.addcar.di

import com.carly.features.addcar.domain.AddNewCarUseCase
import com.carly.features.addcar.domain.FetchStepDataUseCaseUseCase
import com.carly.features.addcar.rp.AddCarRepository
import com.carly.features.addcar.rp.AddCarRepositoryImpl
import com.carly.features.addcar.vm.AddCarViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Koin module for AddCar feature
 */
val addCarModule = module {
    single<AddCarRepository> { AddCarRepositoryImpl(get(), get(), get()) }
    single { FetchStepDataUseCaseUseCase(get()) }
    single { AddNewCarUseCase(get()) }
    viewModel { AddCarViewModel(get(), get()) }
}