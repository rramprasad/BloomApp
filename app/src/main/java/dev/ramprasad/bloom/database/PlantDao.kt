/*
 * Created by Ramprasad Ranganathan on 02/06/21, 2:07 PM
 * Copyright (c) 2021. All rights reserved
 * Last modified 01/06/21, 2:45 PM
 */

package dev.ramprasad.bloom.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface PlantDao {

    @Query("SELECT * FROM plant")
    fun getAll() : Flow<List<Plant>>

    @Query("SELECT * FROM plant WHERE plant_id = :plantId")
    suspend fun findByPlantId(plantId : Int) : Plant

    @Query("SELECT * FROM plant WHERE garden_theme_id = :gardenThemeId")
    suspend fun findByGardenThemeId(gardenThemeId: Int) : List<Plant>

    @Query("SELECT * FROM plant WHERE plant_name LIKE :plantName")
    suspend fun findByPlantName(plantName : String) : List<Plant>

    @Insert
    suspend fun insertAll(vararg plant: Plant)

    @Insert
    suspend fun insert(plant: Plant)

    @Delete
    suspend fun delete(plant: Plant)
}