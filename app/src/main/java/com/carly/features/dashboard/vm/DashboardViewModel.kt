package com.carly.features.dashboard.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carly.features.dashboard.domain.GetSelectedCarUseCase
import com.carly.features.dashboard.domain.LoadInitDataUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
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
            getSelectedCarUseCase.invoke()
            _state.value = DashboardState.NoCarAvailable
        }
    }

}