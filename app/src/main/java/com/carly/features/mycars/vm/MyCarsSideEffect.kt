package com.carly.features.mycars.vm

sealed interface MyCarsSideEffect {
    data object NavigateToAddNewCar : MyCarsSideEffect
    data object ShowError : MyCarsSideEffect
    data object NavigateToBack : MyCarsSideEffect
}