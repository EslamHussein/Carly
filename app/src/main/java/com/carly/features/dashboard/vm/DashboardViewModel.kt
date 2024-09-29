package com.carly.features.dashboard.vm

import com.carly.core.vm.MviViewModel
import com.carly.features.dashboard.domain.GetSelectedCarUseCase
import com.carly.features.dashboard.domain.LoadInitDataUseCase
import kotlinx.coroutines.flow.collectLatest

class DashboardViewModel(
    private val loadInitDataUseCase: LoadInitDataUseCase,
    private val getSelectedCarUseCase: GetSelectedCarUseCase
) : MviViewModel<DashboardState, DashboardSideEffect, DashboardAction>(DashboardState()) {

    init {
        loadInitData()
    }

    private fun loadInitData() = intent {
        loadInitDataUseCase()
            .onSuccess {
                sendAction(DashboardAction.LoadSelectedCar)
            }.onFailure {
                postSideEffect(DashboardSideEffect.ShowError)
            }
    }

    override fun sendAction(action: DashboardAction) {

        when (action) {
            DashboardAction.LoadInitData -> loadInitData()
            DashboardAction.LoadSelectedCar -> collectSelectedCar()
            DashboardAction.OnAddNewCarClicked -> intent { postSideEffect(DashboardSideEffect.NavigateToAddNewCar) }
            DashboardAction.OnMenuClicked -> intent { postSideEffect(DashboardSideEffect.NavigateToCarList) }
        }

    }

    private fun collectSelectedCar() = intent {
        getSelectedCarUseCase()
            .collectLatest {
                if (it != null) {
                    reduce { copy(selectedCarWithFeatures = it) }
                }
            }
    }

}