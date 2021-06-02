/*
 * Created by Ramprasad Ranganathan on 02/06/21, 2:07 PM
 * Copyright (c) 2021. All rights reserved
 * Last modified 02/06/21, 2:07 PM
 */

package dev.ramprasad.bloom.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(version = 1, entities = [User::class, GardenTheme::class, Plant::class])
abstract class AppDatabase : RoomDatabase() {
    abstract fun UserDao() : UserDao
    abstract fun GardenThemeDao() : GardenThemeDao
    abstract fun PlantDao() : PlantDao
}