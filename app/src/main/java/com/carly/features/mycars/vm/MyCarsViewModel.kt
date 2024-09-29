package com.carly.features.mycars.vm

import com.carly.core.vm.MviViewModel
import com.carly.features.mycars.domain.DeleteUserCarUseCase
import com.carly.features.mycars.domain.GetMyCarsUseCase
import com.carly.features.mycars.domain.GetSelectedCarIdUseCase
import com.carly.features.mycars.domain.UpdateSelectedCarUseCase
import com.carly.features.mycars.ui.dto.MyCar
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine

class MyCarsViewModel(
    private val getMyCarsUseCase: GetMyCarsUseCase,
    private val getSelectedCarIdUseCase: GetSelectedCarIdUseCase,
    private val updateSelectedCarUseCase: UpdateSelectedCarUseCase,
    private val deleteCarUseCase: DeleteUserCarUseCase
) :
    MviViewModel<MyCarsState, MyCarsSideEffect, MyCarsAction>(MyCarsState()) {
    override fun sendAction(action: MyCarsAction) {
        when (action) {
            MyCarsAction.LoadCars -> collectMyCars()
            is MyCarsAction.OnCarSelected -> updateSelectedCat(action.myCar)

            is MyCarsAction.OnDeleteCarClicked -> deleteCar(action.myCar)
            MyCarsAction.OnBackClicked -> intent { postSideEffect(MyCarsSideEffect.NavigateToBack) }
            MyCarsAction.OnAddNewCarClicked -> intent { postSideEffect(MyCarsSideEffect.NavigateToAddNewCar) }
        }

    }

    private fun updateSelectedCat(myCar: MyCar) = intent {
        updateSelectedCarUseCase(myCar.id).onSuccess {
            reduce { copy(selectedCarId = myCar.id) }
        }
    }

    private fun deleteCar(myCar: MyCar) {
        intent { deleteCarUseCase(myCar).onSuccess { sendAction(MyCarsAction.LoadCars) } }
    }

    private fun collectMyCars() {
        intent {
            getMyCarsUseCase().combine(getSelectedCarIdUseCase()) { cars, selectedCarId ->
                Pair(cars, selectedCarId)
            }.collectLatest {
                reduce { copy(cars = it.first, selectedCarId = it.second) }
            }
        }
    }
}