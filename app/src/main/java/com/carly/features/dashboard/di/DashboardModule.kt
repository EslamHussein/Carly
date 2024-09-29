package com.carly.features.dashboard.di

import com.carly.features.dashboard.domain.GetSelectedCarUseCase
import com.carly.features.dashboard.domain.LoadInitDataUseCase
import com.carly.features.dashboard.rp.DashboardRepository
import com.carly.features.dashboard.rp.DashboardRepositoryImpl
import com.carly.features.dashboard.vm.DashboardViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val dashboardModule = module {
    single<DashboardRepository> { DashboardRepositoryImpl(get(), get(), get(), get()) }
    viewModel { DashboardViewModel(get()) }
    single { LoadInitDataUseCase(get()) }
    single { GetSelectedCarUseCase(get()) }
}