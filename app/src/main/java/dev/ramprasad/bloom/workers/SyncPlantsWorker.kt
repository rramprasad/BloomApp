/*
 * Created by Ramprasad Ranganathan on 30/06/21, 6:43 PM
 * Copyright (c) 2021. All rights reserved
 * Last modified 30/06/21, 6:43 PM
 */

package dev.ramprasad.bloom.workers

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.google.firebase.firestore.FirebaseFirestore
import dev.ramprasad.bloom.database.AppDatabase
import dev.ramprasad.bloom.database.GardenTheme
import dev.ramprasad.bloom.database.Plant
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


class SyncPlantsWorker(appContext:Context, workerParams:WorkerParameters) : CoroutineWorker(appContext,workerParams) {
    private val LOG_TAG: String = "SyncPlantsWorker"
    @Inject lateinit var fireStoreDatabase:FirebaseFirestore
    @Inject lateinit var appDatabase:AppDatabase

    override suspend fun doWork(): Result {
        var workerResult = Result.failure()
        withContext(Dispatchers.IO){
            fireStoreDatabase.collection("plants")
                .get()
                .addOnSuccessListener { result ->
                    for (document in result){
                        val plantId = document.id
                        val plantData = document.data
                        val plant = Plant(
                            plantId = plantId,
                            plantName = plantData.getValue("plant_name") as String,
                            plantImageUrl = plantData.getValue("plant_image_url") as String,
                            plantDescription = plantData.getValue("plant_description") as String,
                            gardenThemeId = plantData.getValue("garden_theme_id") as String
                        )
                        launch(coroutineContext) {
                            appDatabase.PlantDao().delete(plant)
                            appDatabase.PlantDao().insert(plant = plant)
                        }
                    }
                    workerResult = Result.success()
                }
                .addOnFailureListener {
                    Log.d(LOG_TAG, "SyncPlantsWorker: $it")
                    workerResult = Result.failure()
                }
        }
        return workerResult
    }
}