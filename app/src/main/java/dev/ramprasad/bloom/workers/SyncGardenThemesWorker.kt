/*
 * Created by Ramprasad Ranganathan on 30/06/21, 6:43 PM
 * Copyright (c) 2021. All rights reserved
 * Last modified 30/06/21, 6:43 PM
 */

package dev.ramprasad.bloom.workers

import android.content.Context
import android.util.Log
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.google.firebase.firestore.FirebaseFirestore
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dev.ramprasad.bloom.database.AppDatabase
import dev.ramprasad.bloom.database.GardenTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltWorker
class SyncGardenThemesWorker @AssistedInject constructor(@Assisted appContext:Context, @Assisted workerParams:WorkerParameters, val fireStoreDatabase:FirebaseFirestore, val appDatabase:AppDatabase) : CoroutineWorker(appContext,workerParams) {
    private val LOG_TAG: String = "SyncGardenThemesWorker"
    //@Inject lateinit var fireStoreDatabase:FirebaseFirestore
    //@Inject lateinit var appDatabase:AppDatabase

    override suspend fun doWork(): Result {
        Log.d("SyncGardenThemesWorker", "create: ")
        var workerResult = Result.failure()
        withContext(Dispatchers.IO){
            fireStoreDatabase.collection("gardenthemes")
                .get()
                .addOnSuccessListener { result ->
                    for (document in result){
                        val gardenThemeId = document.id
                        val gardenThemeData = document.data
                        val gardenTheme = GardenTheme(gardenThemeId, gardenThemeData.getValue("theme_name") as String,  gardenThemeData.getValue("theme_image_url") as String)
                        launch(coroutineContext) {
                            appDatabase.GardenThemeDao().delete(gardenTheme)
                            appDatabase.GardenThemeDao().insert(gardenTheme = gardenTheme)
                        }
                    }
                    workerResult = Result.success()
                }
                .addOnFailureListener {
                    Log.d(LOG_TAG, "SyncGardenThemesWorker: $it")
                    workerResult = Result.failure()
                }
        }
        return workerResult
    }
}