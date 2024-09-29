package com.carly.features.addcar.vm

data class AddCarState(
    val searchQuery: String = "",
    val newCar: CreateUserCar = CreateUserCar(),
    val currentStep: Step = Step.SelectBrand,
    val currentStepList: List<SelectionItem> = emptyList(),
    val filtered: List<SelectionItem> = currentStepList
)


