package dev.ramprasad.bloom.dependencyinjection

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
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
                    prePopulateDatabase(applicationContext)
                }
            })
            .build()
    }

    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth {
        return Firebase.auth
    }

    private fun prePopulateDatabase(applicationContext: Context) {
        GlobalScope.launch {
            val appDatabase = provideRoomDatabase(applicationContext)
            appDatabase.UserDao().insert(User(0, "ramtrg@gmail.com", "Ramprasad"))
            appDatabase.GardenThemeDao().insertAll(
                GardenTheme(0, "Desert chic","https://raw.githubusercontent.com/rramprasad/BloomAppAssets/main/Themes/theme1.jpg"),
                GardenTheme(0, "Tiny terrariums","https://raw.githubusercontent.com/rramprasad/BloomAppAssets/main/Themes/theme2.jpg"),
                GardenTheme(0, "Jungle vibes","https://raw.githubusercontent.com/rramprasad/BloomAppAssets/main/Themes/theme3.jpg"),
                GardenTheme(0, "Easy care","https://raw.githubusercontent.com/rramprasad/BloomAppAssets/main/Themes/theme4.jpg"),
                GardenTheme(0, "Statements","https://raw.githubusercontent.com/rramprasad/BloomAppAssets/main/Themes/theme5.jpg")
            )
            appDatabase.PlantDao().insertAll(
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