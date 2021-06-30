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

@Entity(tableName = "gardentheme")
data class GardenTheme @Inject constructor(
    @PrimaryKey(autoGenerate = false) @ColumnInfo(name = "theme_id") val themeId : String,
    @ColumnInfo(name = "theme_name") val themeName : String,
    @ColumnInfo(name = "theme_image_url") val themeImageUrl : String)