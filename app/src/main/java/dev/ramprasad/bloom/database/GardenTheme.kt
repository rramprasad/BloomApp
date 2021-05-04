package dev.ramprasad.bloom.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "gardentheme")
data class GardenTheme(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "theme_id") val themeId : Int = 0,
    @ColumnInfo(name = "theme_name") val themeName : String,
    @ColumnInfo(name = "theme_image_url") val themeImageUrl : String)