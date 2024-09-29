package com.carly.features.mycars.vm

import com.carly.features.mycars.ui.dto.MyCar

sealed interface MyCarsAction {
    data object LoadCars : MyCarsAction
    data object OnBackClicked : MyCarsAction
    data class OnCarSelected(val myCar: MyCar) : MyCarsAction
    data class OnDeleteCarClicked(val myCar: MyCar) : MyCarsAction
    data object OnAddNewCarClicked : MyCarsAction
}