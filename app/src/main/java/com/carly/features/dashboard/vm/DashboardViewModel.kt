package com.carly.features.dashboard.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carly.features.dashboard.domain.GetSelectedCarUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class DashboardViewModel(
    private val getSelectedCarUseCase: GetSelectedCarUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<DashboardState>(DashboardState.NoCarAvailable)
    val state = _state.asStateFlow()

    init {
        loadInitData()
    }

    private fun loadInitData() {
        viewModelScope.launch {
            _state.value = DashboardState.Loading
            getSelectedCarUseCase().collectLatest { selectedCar ->
                if (selectedCar != null) {
                    _state.value = DashboardState.CarSelected(selectedCar)
                } else {
                    _state.value = DashboardState.NoCarAvailable
                }
            }

        }
    }

}