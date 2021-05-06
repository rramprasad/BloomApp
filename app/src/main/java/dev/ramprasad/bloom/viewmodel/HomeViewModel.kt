package dev.ramprasad.bloom.viewmodel

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
