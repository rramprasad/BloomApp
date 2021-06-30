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

@Dao
interface GardenThemeDao {

    @Query("SELECT * FROM gardentheme")
    suspend fun getAll() : List<GardenTheme>

    @Insert
    suspend fun insert(gardenTheme : GardenTheme)

    @Insert
    suspend fun insertAll(vararg gardenTheme : GardenTheme)

    @Delete
    suspend fun delete(gardenThemeId: GardenTheme)
}