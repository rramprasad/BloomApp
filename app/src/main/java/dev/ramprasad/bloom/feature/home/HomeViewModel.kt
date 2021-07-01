/*
 * Created by Ramprasad Ranganathan on 03/06/21, 5:48 PM
 * Copyright (c) 2021. All rights reserved
 * Last modified 03/06/21, 5:45 PM
 */

package dev.ramprasad.bloom.feature.home

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.ramprasad.bloom.database.GardenTheme
import dev.ramprasad.bloom.database.Plant
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val savedStateHandle: SavedStateHandle, private val homeRepository: HomeRepository) : ViewModel() {

    private val _gardenThemesListState:MutableStateFlow<List<GardenTheme>> = MutableStateFlow(emptyList())
    val gardenThemesListState: StateFlow<List<GardenTheme>> = _gardenThemesListState

    private val _plantsListState:MutableStateFlow<List<Plant>> = MutableStateFlow(emptyList())
    val plantsListState: StateFlow<List<Plant>> = _plantsListState

    init {
        Log.d("FlowLog", "HomeViewModel initialized")
        loadGardenThemesList()
        loadPlantsList()
    }

    private fun loadGardenThemesList() {
        viewModelScope.launch {
            homeRepository.getGardenThemesList().stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = emptyList()
            ).collect {
                Log.d("FlowLog", "loadGardenThemesList: $it")
                _gardenThemesListState.value = it
            }
        }
    }

    private fun loadPlantsList() {
        viewModelScope.launch {
            homeRepository.getPlantsList().stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = emptyList()
            ).collect{
                Log.d("FlowLog", "loadPlantsList: $it")
                _plantsListState.value = it
            }
        }
    }
}
