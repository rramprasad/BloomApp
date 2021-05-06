package dev.ramprasad.bloom.repository

import dev.ramprasad.bloom.database.AppDatabase
import dev.ramprasad.bloom.database.GardenTheme
import dev.ramprasad.bloom.database.Plant
import javax.inject.Inject

class HomeRepository @Inject constructor() {

    @Inject
    lateinit var database : AppDatabase

    suspend fun getGardenThemesList(): List<GardenTheme> {
        val gardenThemeDao = database.GardenThemeDao()
        return gardenThemeDao.getAll()

    }

    suspend fun getPlantsList(): List<Plant> {
        val plantDao = database.PlantDao()
        return plantDao.getAll()
    }
}
