package com.carly.core.di

import com.carly.core.dispatcher.DispatcherProvider
import com.carly.core.dispatcher.DispatcherProviderImpl
import org.koin.dsl.module

val coroutinesModule = module {
    single<DispatcherProvider> { DispatcherProviderImpl() }
}