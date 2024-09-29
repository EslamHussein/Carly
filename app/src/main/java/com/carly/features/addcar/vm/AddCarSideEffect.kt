package com.carly.features.addcar.vm

/**
 * Side effects for AddCar feature
 */
sealed interface AddCarSideEffect {
    data object ShowError : AddCarSideEffect
    data object NavigateBack : AddCarSideEffect
    data object NativeToHome : AddCarSideEffect
}