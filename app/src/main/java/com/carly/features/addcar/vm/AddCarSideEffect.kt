package com.carly.features.addcar.vm

sealed interface AddCarSideEffect {
    data object NavigateBack : AddCarSideEffect
}