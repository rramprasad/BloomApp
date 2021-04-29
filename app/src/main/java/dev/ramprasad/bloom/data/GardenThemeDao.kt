package dev.ramprasad.bloom.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface GardenThemeDao {

    @Query("SELECT * FROM gardentheme")
    suspend fun getAll() : List<GardenTheme>

    @Insert
    suspend fun insertAll(vararg gardenTheme : GardenTheme)

    @Delete
    suspend fun delete(gardenTheme: GardenTheme)
}