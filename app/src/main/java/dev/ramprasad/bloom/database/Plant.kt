package dev.ramprasad.bloom.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "plant")
data class Plant(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "plant_id") val plantId : Int = 0,
    @ColumnInfo(name = "plant_name") val plantName : String,
    @ColumnInfo(name = "plant_image_url") val plantImageUrl : String,
    @ColumnInfo(name = "plant_description") val plantDescription : String,
    @ColumnInfo(name = "garden_theme_id") val gardenThemeId : Int)
