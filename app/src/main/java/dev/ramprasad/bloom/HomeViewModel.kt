package dev.ramprasad.bloom

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.ramprasad.bloom.RoomDatabaseHelper
import dev.ramprasad.bloom.data.AppDatabase
import dev.ramprasad.bloom.data.GardenTheme
import kotlinx.coroutines.launch

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    private var homeRepository: HomeRepository = HomeRepository(application = getApplication())
    MutableLiveData()

    fun getGardenThemesList() {
        viewModelScope.launch {
            homeRepository.getGardenThemesList()
        }
    }

    fun getPlantsList(){
        viewModelScope.launch {
            homeRepository.getGardenThemesList()
        }
    }
}
