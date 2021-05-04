package dev.ramprasad.bloom.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

object RoomDatabaseHelper {

    fun getInstance(context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java,"bloom-app-db")
            .addCallback(object : RoomDatabase.Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)
                    prePopulateDatabase(context)
                }
            })
            .build()
    }

    private fun prePopulateDatabase(context: Context) {
        GlobalScope.launch {
            getInstance(context = context).UserDao()
                .insert(User(0, "ramtrg@gmail.com", "Ramprasad"))
            getInstance(context = context).GardenThemeDao().insertAll(
                GardenTheme(0, "Desert chic","https://raw.githubusercontent.com/rramprasad/BloomAppAssets/main/Themes/theme1.jpg"),
                GardenTheme(0, "Tiny terrariums","https://raw.githubusercontent.com/rramprasad/BloomAppAssets/main/Themes/theme2.jpg"),
                GardenTheme(0, "Jungle vibes","https://raw.githubusercontent.com/rramprasad/BloomAppAssets/main/Themes/theme3.jpg"),
                GardenTheme(0, "Easy care","https://raw.githubusercontent.com/rramprasad/BloomAppAssets/main/Themes/theme4.jpg"),
                GardenTheme(0, "Statements","https://raw.githubusercontent.com/rramprasad/BloomAppAssets/main/Themes/theme5.jpg")
            )
            getInstance(context = context).PlantDao().insertAll(
                Plant(0, "Monstera","https://raw.githubusercontent.com/rramprasad/BloomAppAssets/main/Plants/plant1.jpg","This is a description",1),
                Plant(0, "Aglaonema","https://raw.githubusercontent.com/rramprasad/BloomAppAssets/main/Plants/plant2.jpg","This is a description",1),
                Plant(0, "Peace lily","https://raw.githubusercontent.com/rramprasad/BloomAppAssets/main/Plants/plant3.jpg","This is a description",1),
                Plant(0, "Fiddle leaf tree","https://raw.githubusercontent.com/rramprasad/BloomAppAssets/main/Plants/plant4.jpg","This is a description",1),
                Plant(0, "Snake plant","https://raw.githubusercontent.com/rramprasad/BloomAppAssets/main/Plants/plant5.jpg","This is a description",1),
                Plant(0, "Pothos","https://raw.githubusercontent.com/rramprasad/BloomAppAssets/main/Plants/plant6.jpg","This is a description",1)
            )
        }
    }
}