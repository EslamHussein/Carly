package com.carly.features.addcar.vm

import com.carly.core.vm.MviViewModel
import com.carly.features.addcar.domain.AddNewCarUseCase
import com.carly.features.addcar.domain.FetchStepDataUseCaseUseCase
import kotlinx.coroutines.flow.collectLatest

/**
 * ViewModel for AddCar feature
 */
class AddCarViewModel(
    private val fetchStepDataUseCaseUseCase: FetchStepDataUseCaseUseCase,
    private val addNewCarUseCase: AddNewCarUseCase
) :
    MviViewModel<AddCarState, AddCarSideEffect, AddCarAction>(AddCarState()) {

    override fun sendAction(action: AddCarAction) {

        when (action) {
            is AddCarAction.SearchQueryChanged -> {
                reduce {
                    copy(
                        searchQuery = action.query,
                        filtered = currentStepList.filter {
                            it.name.contains(action.query, ignoreCase = true)
                        }
                    )
                }
            }

            is AddCarAction.OnItemSelected -> {
                handelOnItemSelected(action)
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

    private fun handelOnItemSelected(action: AddCarAction.OnItemSelected) = intent {
        reduce {
            copy(
                searchQuery = "",
                newCar = when (currentStep) {
                    Step.SelectBrand -> newCar.copy(
                        brand = action.item
                    )

                    Step.SelectSeries -> newCar.copy(
                        series = action.item
                    )

                    Step.SelectBuildYear -> newCar.copy(
                        year = action.item
                    )

                    Step.SelectFuelType -> newCar.copy(
                        fuelType = action.item
                    )
                }
            )
        }.also {

            if (state.value.currentStep != Step.SelectFuelType) {
                sendAction(AddCarAction.LoadData(getNextStep(state.value.currentStep)))
            } else {
                addNewCar(state.value.newCar)
            }
        }
    }

    private fun addNewCar(newCar: CreateUserCar) = intent {

        addNewCarUseCase(newCar)
            .onSuccess {
                postSideEffect(AddCarSideEffect.NativeToHome)
            }.onFailure {
                postSideEffect(AddCarSideEffect.ShowError)
            }
    }

    private fun getNextStep(currentStep: Step) = when (currentStep) {
        Step.SelectBrand -> Step.SelectSeries
        Step.SelectSeries -> Step.SelectBuildYear
        Step.SelectBuildYear -> Step.SelectFuelType
        else -> currentStep
    }

    private fun handleBackPressed() = intent {
        if (state.value.currentStep == Step.SelectBrand) {
            postSideEffect(AddCarSideEffect.NavigateBack)
        } else {
            reduce {
                val previousStep = getPreviousStep(state.value.currentStep)
                copy(
                    currentStep = previousStep,
                    searchQuery = "",
                    newCar = resetNewCar(state.value.newCar, previousStep)
                )
            }
        }
    }

    private fun resetNewCar(currentCar: CreateUserCar, previousStep: Step): CreateUserCar {
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

            Step.SelectFuelType -> currentCar.copy(
                fuelType = null
            )
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
        intent {
            reduce { copy(currentStep = currentStep) }
            fetchStepDataUseCaseUseCase(
                FetchStepDataUseCaseUseCase.Params.create(
                    currentStep = currentStep,
                    brand = state.value.newCar.brand,
                    series = state.value.newCar.series,
                    year = state.value.newCar.year
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





