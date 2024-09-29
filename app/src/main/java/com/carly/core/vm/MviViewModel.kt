package com.carly.core.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

abstract class MviViewModel<STATE : Any, SIDE_EFFECT : Any, ACTION : Any>(
    initialState: STATE
) : ViewModel() {

    private val _state: MutableStateFlow<STATE> = MutableStateFlow(initialState)
    val state: StateFlow<STATE> = _state

    private val _sideEffect = Channel<SIDE_EFFECT>(Channel.BUFFERED)
    val sideEffect: Flow<SIDE_EFFECT> = _sideEffect.receiveAsFlow()

    protected fun reduce(reducer: STATE.() -> STATE) {
        _state.update { _state.value.reducer() }
    }

    protected fun intent(
        transform: suspend MviViewModel<STATE, SIDE_EFFECT, ACTION>.() -> Unit
    ): Job {
        return viewModelScope.launch {
            transform()
        }
    }

    suspend fun postSideEffect(sideEffect: SIDE_EFFECT) {
        _sideEffect.send(sideEffect)
    }

    abstract fun sendAction(action: ACTION)

}