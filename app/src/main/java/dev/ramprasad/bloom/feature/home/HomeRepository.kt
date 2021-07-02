/*
 * Created by Ramprasad Ranganathan on 02/06/21, 2:07 PM
 * Copyright (c) 2021. All rights reserved
 * Last modified 01/06/21, 2:45 PM
 */

package dev.ramprasad.bloom.feature.home

import android.util.Log
import dev.ramprasad.bloom.database.AppDatabase
import dev.ramprasad.bloom.database.GardenTheme
import dev.ramprasad.bloom.database.Plant
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class HomeRepository @Inject constructor() {

    @Inject
    lateinit var database : AppDatabase

    // Flow of List data instead of single shot list fetch
    // Get Garden Themes list from local database
    suspend fun getGardenThemesList(): Flow<List<GardenTheme>> {
        val gardenThemeDao = database.GardenThemeDao()
        return gardenThemeDao.getAll()
    }

    // Get plants list from local database
    suspend fun getPlantsList(): Flow<List<Plant>> {
        val plantDao = database.PlantDao()
        return plantDao.getAll()
    }
}
