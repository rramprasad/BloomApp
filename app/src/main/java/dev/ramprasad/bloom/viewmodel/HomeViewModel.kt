/*
 * Created by Ramprasad Ranganathan on 02/06/21, 2:07 PM
 * Copyright (c) 2021. All rights reserved
 * Last modified 01/06/21, 2:45 PM
 */

package dev.ramprasad.bloom.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.ramprasad.bloom.database.GardenTheme
import dev.ramprasad.bloom.database.Plant
import dev.ramprasad.bloom.repository.HomeRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {

    @Inject
    lateinit var homeRepository: HomeRepository

    @Inject
    lateinit var gardenThemesListLiveData : MutableLiveData<List<GardenTheme>>

    @Inject
    lateinit var plantsListLiveData:MutableLiveData<List<Plant>>

    @Inject
    lateinit var emailLiveData: MutableLiveData<String>

    @Inject
    lateinit var passwordLiveData: MutableLiveData<String>

    fun onEmailChange(newEmail:String) {
        emailLiveData.value = newEmail
    }

    fun onPasswordChange(newPassword : String) {
        passwordLiveData.postValue(newPassword)
    }

    fun loadGardenThemesList() {
        viewModelScope.launch {
            val gardenThemesList = homeRepository.getGardenThemesList()
            gardenThemesListLiveData.postValue(gardenThemesList)
        }
    }

    fun loadPlantsList() {
        viewModelScope.launch {
            val plantsList = homeRepository.getPlantsList()
            plantsListLiveData.postValue(plantsList)
        }
    }

    fun onLogin() {
        viewModelScope.launch {
            val loginResult = homeRepository.login(emailLiveData.value,passwordLiveData.value)
        }
    }
}
