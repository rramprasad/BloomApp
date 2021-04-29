package dev.ramprasad.bloom

import android.app.Application
import androidx.compose.runtime.rememberCoroutineScope
import androidx.lifecycle.viewModelScope
import dev.ramprasad.bloom.data.AppDatabase
import dev.ramprasad.bloom.data.GardenTheme
import kotlinx.coroutines.launch

class HomeRepository(application: Application) {

    var database: AppDatabase = RoomDatabaseHelper.getInstance(application.applicationContext)

    fun getGardenThemesList()/*: List<GardenTheme>*/ {

        /*viewModelScope.launch {
            val gardenThemeDao = database.GardenThemeDao()
            val gardenThemesList = gardenThemeDao.getAll()
            return gardenThemesList
        }*/
    }
}