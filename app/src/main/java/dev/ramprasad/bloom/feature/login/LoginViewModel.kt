/*
 * Created by Ramprasad Ranganathan on 03/06/21, 5:48 PM
 * Copyright (c) 2021. All rights reserved
 * Last modified 03/06/21, 5:45 PM
 */

package dev.ramprasad.bloom.feature.login

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

    /*private val _emailState: MutableStateFlow<String> = MutableStateFlow("")
    val emailState : StateFlow<String> = _emailState

    private val _passwordState: MutableStateFlow<String> = MutableStateFlow("")
    val passwordState : StateFlow<String> = _passwordState


    fun onEmailChange(newEmail:String) {
        _emailState.value = newEmail
    }

    fun onPasswordChange(newPassword : String) {
        _passwordState.value = newPassword
    }*/

    private val _loginResultState: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val loginResultState : StateFlow<Boolean> = _loginResultState

    @ExperimentalCoroutinesApi
    fun onLogin(email: String, password: String) {
        viewModelScope.launch {
            loginRepository.login(email,password).mapLatest { loginResult ->
                _loginResultState.value = loginResult
            }.stateIn(
                scope = viewModelScope,
                //started = SharingStarted.WhileSubscribed(5000),
                started = SharingStarted.Eagerly,
                initialValue = false
            )
        }
    }
}
