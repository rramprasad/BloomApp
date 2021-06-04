/*
 * Created by Ramprasad Ranganathan on 03/06/21, 5:48 PM
 * Copyright (c) 2021. All rights reserved
 * Last modified 03/06/21, 5:45 PM
 */

package dev.ramprasad.bloom.feature.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val loginRepository: LoginRepository) : ViewModel() {

    /**
     * UI States
     */
    private val _loginResultState: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val loginResultState : StateFlow<Boolean> = _loginResultState

    /**
     * UI Events
     */
    @ExperimentalCoroutinesApi
    fun onLogin(email: String, password: String) {
        Log.d("compose", "onLogin on LoginViewModel")
        viewModelScope.launch(Dispatchers.IO) {
            loginRepository.login(email,password).stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = false
            ).collect {
                _loginResultState.value = it
            }
        }
    }
}
