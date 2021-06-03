/*
 * Created by Ramprasad Ranganathan on 02/06/21, 2:07 PM
 * Copyright (c) 2021. All rights reserved
 * Last modified 01/06/21, 2:45 PM
 */

package dev.ramprasad.bloom.feature.home

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
