/*
 * Created by Ramprasad Ranganathan on 29/06/21, 2:44 PM
 * Copyright (c) 2021. All rights reserved
 * Last modified 29/06/21, 2:44 PM
 */

package dev.ramprasad.bloom.feature.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
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
    val loginResultState: StateFlow<Boolean> = _loginResultState

    private val _signUpResultState: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val signUpResultState: StateFlow<Boolean> = _signUpResultState

    private val _userLoggedIn: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val userLoggedIn: StateFlow<Boolean> = _userLoggedIn

    init {
        isUserLoggedIn()
    }

    /**
     * UI Events
     */
    @ExperimentalCoroutinesApi
    fun onLogin(email: String, password: String) {
        viewModelScope.launch {
            loginRepository.login(email,password).stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = false
            ).collect {
                _loginResultState.value = it
            }
        }
    }

    private fun isUserLoggedIn() {
        viewModelScope.launch {
            _userLoggedIn.value = loginRepository.isUserLoggedIn()
        }
    }

    fun onSignUp(email: String, password: String) {
        viewModelScope.launch {
            loginRepository.signUp(email, password).stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = false
            ).collect {
                _signUpResultState.value = it
            }
        }
    }

    fun onSignOut(){
        viewModelScope.launch {
            loginRepository.signOut()
            isUserLoggedIn()
        }
    }
}
