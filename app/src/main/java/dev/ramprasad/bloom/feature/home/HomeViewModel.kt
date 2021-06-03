/*
 * Created by Ramprasad Ranganathan on 03/06/21, 5:48 PM
 * Copyright (c) 2021. All rights reserved
 * Last modified 03/06/21, 5:45 PM
 */

package dev.ramprasad.bloom.feature.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.ramprasad.bloom.database.GardenTheme
import dev.ramprasad.bloom.database.Plant
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val homeRepository: HomeRepository) : ViewModel() {

    @Inject
    lateinit var gardenThemesListLiveData : MutableLiveData<List<GardenTheme>>

    @Inject
    lateinit var plantsListLiveData:MutableLiveData<List<Plant>>

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
}
