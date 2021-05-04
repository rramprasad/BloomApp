package dev.ramprasad.bloom.viewmodel

import android.app.Application
import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.ramprasad.bloom.repository.HomeRepository
import dev.ramprasad.bloom.database.GardenTheme
import dev.ramprasad.bloom.database.Plant
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(val handle: SavedStateHandle) : ViewModel() {

    @Inject
    lateinit var homeRepository: HomeRepository

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
