package com.carly.features.addcar.vm

sealed interface AddCarAction {
    data class LoadData(val currentStep: Step) : AddCarAction
    data class SearchQueryChanged(val query: String) : AddCarAction
    data class OnItemSelected(val item: SelectionItem) : AddCarAction
    data object BackPressed : AddCarAction
}