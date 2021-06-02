/*
 * Created by Ramprasad Ranganathan on 02/06/21, 2:07 PM
 * Copyright (c) 2021. All rights reserved
 * Last modified 01/06/21, 2:45 PM
 */

package dev.ramprasad.bloom.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import javax.inject.Inject

@Entity(tableName = "plant")
data class Plant @Inject constructor(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "plant_id") val plantId : Int = 0,
    @ColumnInfo(name = "plant_name") val plantName : String,
    @ColumnInfo(name = "plant_image_url") val plantImageUrl : String,
    @ColumnInfo(name = "plant_description") val plantDescription : String,
    @ColumnInfo(name = "garden_theme_id") val gardenThemeId : Int)
