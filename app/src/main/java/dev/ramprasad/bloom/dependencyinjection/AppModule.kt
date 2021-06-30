/*
 * Created by Ramprasad Ranganathan on 02/06/21, 2:07 PM
 * Copyright (c) 2021. All rights reserved
 * Last modified 01/06/21, 6:15 PM
 */

package dev.ramprasad.bloom.dependencyinjection

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.startup.AppInitializer
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.ramprasad.bloom.database.AppDatabase
import dev.ramprasad.bloom.database.GardenTheme
import dev.ramprasad.bloom.database.Plant
import dev.ramprasad.bloom.database.User
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideRoomDatabase(@ApplicationContext applicationContext: Context): AppDatabase {
        return Room.databaseBuilder(applicationContext, AppDatabase::class.java,"bloom-app-db")
            .addCallback(object : RoomDatabase.Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)
                    //prePopulateDatabase(applicationContext)
                }
            })
            .build()
    }

    @Provides
    @Singleton
    fun provideFirebaseAuth(@ApplicationContext applicationContext: Context): FirebaseAuth {
        return Firebase.auth
    }

    @Provides
    @Singleton
    fun provideFireStoreInstance(): FirebaseFirestore {
        return Firebase.firestore
    }

    @Provides
    @Singleton
    fun provideAppInitializer(@ApplicationContext applicationContext: Context): AppInitializer {
        return AppInitializer.getInstance(applicationContext)
    }

    private fun prePopulateDatabase(@ApplicationContext applicationContext: Context) {
        GlobalScope.launch {
            val appDatabase = provideRoomDatabase(applicationContext)
            appDatabase.UserDao().insert(User(0, "ramtrg@gmail.com", "Ramprasad"))
            appDatabase.GardenThemeDao().insertAll(
                GardenTheme("A", "Desert chic","https://raw.githubusercontent.com/rramprasad/BloomAppAssets/main/Themes/theme1.jpg"),
                GardenTheme("B", "Tiny terrariums","https://raw.githubusercontent.com/rramprasad/BloomAppAssets/main/Themes/theme2.jpg"),
                GardenTheme("C", "Jungle vibes","https://raw.githubusercontent.com/rramprasad/BloomAppAssets/main/Themes/theme3.jpg"),
                GardenTheme("D", "Easy care","https://raw.githubusercontent.com/rramprasad/BloomAppAssets/main/Themes/theme4.jpg"),
                GardenTheme("E", "Statements","https://raw.githubusercontent.com/rramprasad/BloomAppAssets/main/Themes/theme5.jpg")
            )
            appDatabase.PlantDao().insertAll(
                Plant("Z", "Monstera","https://raw.githubusercontent.com/rramprasad/BloomAppAssets/main/Plants/plant1.jpg","This is a description","A"),
                Plant("Y", "Aglaonema","https://raw.githubusercontent.com/rramprasad/BloomAppAssets/main/Plants/plant2.jpg","This is a description","A"),
                Plant("X", "Peace lily","https://raw.githubusercontent.com/rramprasad/BloomAppAssets/main/Plants/plant3.jpg","This is a description","B"),
                Plant("W", "Fiddle leaf tree","https://raw.githubusercontent.com/rramprasad/BloomAppAssets/main/Plants/plant4.jpg","This is a description","C"),
                Plant("V", "Snake plant","https://raw.githubusercontent.com/rramprasad/BloomAppAssets/main/Plants/plant5.jpg","This is a description","D"),
                Plant("U", "Pothos","https://raw.githubusercontent.com/rramprasad/BloomAppAssets/main/Plants/plant6.jpg","This is a description","E")
            )
        }
    }
}