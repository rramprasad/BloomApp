package dev.ramprasad.bloom.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(version = 1, entities = [User::class, GardenTheme::class, Plant::class])
abstract class AppDatabase : RoomDatabase() {
    abstract fun UserDao() : UserDao
    abstract fun GardenThemeDao() : GardenThemeDao
    abstract fun PlantDao() : PlantDao
}