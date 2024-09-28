package com.carly.features.addcar.vm

import com.carly.core.vm.MviViewModel
import com.carly.features.addcar.domain.FetchStepDataUseCaseUseCase
import kotlinx.coroutines.flow.collectLatest


class AddCarViewModel(private val fetchStepDataUseCaseUseCase: FetchStepDataUseCaseUseCase) :
    MviViewModel<AddCarState, AddCarSideEffect, AddCarAction>(AddCarState()) {

    override fun sendAction(action: AddCarAction) {

        when (action) {
            is AddCarAction.SearchQueryChanged -> {
                reduce {
                    copy(
                        searchQuery = action.query,
                        filtered = state.value.currentStepList.filter {
                            it.name.contains(action.query, ignoreCase = true)
                        }
                    )
                }
            }

            is AddCarAction.OnItemSelected -> {
                reduce {
                    copy(
                        searchQuery = "",
                        currentStep = getNextStep(),
                        newCar = when (state.value.currentStep) {
                            Step.SelectBrand -> state.value.newCar.copy(
                                brand = action.item
                            )

                            Step.SelectSeries -> state.value.newCar.copy(
                                series = action.item
                            )

                            Step.SelectBuildYear -> state.value.newCar.copy(
                                year = action.item
                            )

                            Step.SelectFuelType -> state.value.newCar.copy(
                                fuelType = action.item
                            )
                        }
                    )
                }.also {
                    sendAction(AddCarAction.LoadData(state.value.currentStep))
                }
            }

            is AddCarAction.LoadData -> {
                loadData(
                    currentStep = action.currentStep
                )
            }

            AddCarAction.BackPressed -> handleBackPressed()
                .also {
                    sendAction(AddCarAction.LoadData(state.value.currentStep))
                }
        }

    }

    private fun getNextStep() = when (state.value.currentStep) {
        Step.SelectBrand -> Step.SelectSeries
        Step.SelectSeries -> Step.SelectBuildYear
        Step.SelectBuildYear -> Step.SelectFuelType
        else -> state.value.currentStep
    }

    private fun handleBackPressed() = intent { state ->
        if (state.currentStep == Step.SelectBrand) {
            // Trigger back navigation instead of going to the previous step
            postSideEffect(AddCarSideEffect.NavigateBack)
        } else {
            reduce {
                val previousStep = getPreviousStep(state.currentStep)
                copy(
                    currentStep = previousStep,
                    searchQuery = "",
                    newCar = resetNewCar(state.newCar, previousStep)
                )
            }
        }
    }

    private fun resetNewCar(currentCar: UserCar, previousStep: Step): UserCar {
        return when (previousStep) {
            Step.SelectBrand -> currentCar.copy(
                brand = null,
                series = null,
                year = null,
                fuelType = null
            )

            Step.SelectSeries -> currentCar.copy(
                series = null,
                year = null,
                fuelType = null
            )

            Step.SelectBuildYear -> currentCar.copy(
                year = null,
                fuelType = null
            )

            else -> currentCar
        }
    }

    private fun getPreviousStep(currentStep: Step) = when (currentStep) {
        Step.SelectSeries -> Step.SelectBrand
        Step.SelectBuildYear -> Step.SelectSeries
        Step.SelectFuelType -> Step.SelectBuildYear
        else -> currentStep
    }

    private fun loadData(
        currentStep: Step,
    ) {
        intent { state ->
            fetchStepDataUseCaseUseCase(
                FetchStepDataUseCaseUseCase.Params.create(
                    currentStep = currentStep,
                    brand = state.newCar.brand,
                    series = state.newCar.series,
                    year = state.newCar.year
                )
            ).collectLatest {
                reduce {
                    copy(
                        currentStepList = it,
                        filtered = it
                    )
                }
            }
        }
    }

}





