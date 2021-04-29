package dev.ramprasad.bloom.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PlantDao {

    @Query("SELECT * FROM plant")
    suspend fun getAll() : List<Plant>

    @Query("SELECT * FROM plant WHERE plant_id = :plantId")
    suspend fun findByPlantId(plantId : Int) : Plant

    @Query("SELECT * FROM plant WHERE garden_theme_id = :gardenThemeId")
    suspend fun findByGardenThemeId(gardenThemeId: Int) : List<Plant>

    @Query("SELECT * FROM plant WHERE plant_name LIKE :plantName")
    suspend fun findByPlantName(plantName : String) : List<Plant>

    @Insert
    suspend fun insertAll(vararg plant: Plant)

    @Delete
    suspend fun delete(plant: Plant)
}