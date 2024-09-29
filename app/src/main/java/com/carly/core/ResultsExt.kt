package com.carly.core

internal fun <T> T.asSuccess() = Result.success(this)
internal fun <T> Throwable.asFailure() = Result.failure<T>(this)
