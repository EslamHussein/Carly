package com.carly.features.addcar.vm

data class AddCarState(
    val searchQuery: String = "",
    val newCar: UserCar = UserCar(),
    val currentStep: Step = Step.SelectBrand,
    val currentStepList: List<SelectionItem> = emptyList(),
    val filtered: List<SelectionItem> = currentStepList
)


