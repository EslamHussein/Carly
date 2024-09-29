package com.carly.features.addcar.vm

/**
 * Side effects for AddCar feature
 */
sealed interface AddCarSideEffect {
    data class ShowError(val throwable: Throwable) : AddCarSideEffect
    data object NavigateBack : AddCarSideEffect
    data object NativeToHome : AddCarSideEffect
}