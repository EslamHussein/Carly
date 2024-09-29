package com.carly.features.mycars.di

import com.carly.features.mycars.domain.DeleteUserCarUseCase
import com.carly.features.mycars.domain.GetMyCarsUseCase
import com.carly.features.mycars.domain.GetSelectedCarIdUseCase
import com.carly.features.mycars.domain.UpdateSelectedCarUseCase
import com.carly.features.mycars.rp.MyCarsRepository
import com.carly.features.mycars.rp.MyCarsRepositoryImpl
import com.carly.features.mycars.vm.MyCarsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val myCarsModule = module {
    viewModel { MyCarsViewModel(get(), get(), get(), get()) }
    factory<MyCarsRepository> { MyCarsRepositoryImpl(get(), get(), get()) }
    single { GetMyCarsUseCase(get()) }
    single { GetSelectedCarIdUseCase(get()) }
    single { UpdateSelectedCarUseCase(get()) }
    single { DeleteUserCarUseCase(get()) }
}