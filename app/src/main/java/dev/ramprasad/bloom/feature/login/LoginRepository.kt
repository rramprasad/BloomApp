/*
 * Created by Ramprasad Ranganathan on 29/06/21, 2:44 PM
 * Copyright (c) 2021. All rights reserved
 * Last modified 29/06/21, 2:44 PM
 */

package dev.ramprasad.bloom.feature.login

import android.util.Log
import androidx.startup.AppInitializer
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dev.ramprasad.bloom.database.AppDatabase
import dev.ramprasad.bloom.database.GardenTheme
import dev.ramprasad.bloom.database.Plant
import dev.ramprasad.bloom.workers.SyncGardenThemesWorker
import dev.ramprasad.bloom.workers.SyncPlantsWorker
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginRepository @Inject constructor() {

    @Inject lateinit var firebaseAuth : FirebaseAuth
    @Inject lateinit var fireStoreDatabase: FirebaseFirestore
    @Inject lateinit var appDatabase: AppDatabase
    private val LOG_TAG: String = "LoginRepository"

    @ExperimentalCoroutinesApi
    suspend fun login(email: String, password: String): Flow<Boolean> {
        return callbackFlow {
            firebaseAuth.signInWithEmailAndPassword(email.trim(), password.trim())
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            CoroutineScope(Dispatchers.IO).launch {
                                syncGardenThemes()
                                syncPlants()
                                trySend(true)
                            }
                        } else {
                            trySend(false)
                        }
                    }
            awaitClose()
        }
    }

    @ExperimentalCoroutinesApi
    suspend fun signUp(email: String, password: String): Flow<Boolean> {
        return callbackFlow {
            firebaseAuth.createUserWithEmailAndPassword(email.trim(), password.trim())
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        trySend(true)
                    } else {
                        trySend(false)
                    }
                }
            awaitClose()
        }
    }

    fun signOut() {
        firebaseAuth.signOut()
    }

    fun isUserLoggedIn(): Boolean {
        firebaseAuth.currentUser?.run {
            return true
        } ?: run {
            return false
        }
    }

    private suspend fun syncGardenThemes() {
        CoroutineScope(Dispatchers.IO).launch {
            fireStoreDatabase.collection("gardenthemes")
                .get()
                .addOnSuccessListener { result ->
                    for (document in result){
                        Log.d(LOG_TAG, "syncGardenThemes: $result")
                        val gardenThemeId = document.id
                        val gardenThemeData = document.data
                        val gardenTheme = GardenTheme(gardenThemeId, gardenThemeData.getValue("theme_name") as String,  gardenThemeData.getValue("theme_image_url") as String)
                        Log.d(LOG_TAG, "syncGardenThemes: $gardenTheme")

                        CoroutineScope(Dispatchers.IO).launch {
                            appDatabase.GardenThemeDao().delete(gardenTheme)
                            appDatabase.GardenThemeDao().insert(gardenTheme = gardenTheme)
                            Log.d(LOG_TAG, "syncGardenThemes: inserted")
                        }
                    }
                }
                .addOnFailureListener {
                    Log.d(LOG_TAG, "SyncGardenThemesWorker: $it")
                }
        }
    }

    private suspend fun syncPlants() {
        CoroutineScope(Dispatchers.IO).launch {
            fireStoreDatabase.collection("plants")
                .get()
                .addOnSuccessListener { result ->
                    Log.d(LOG_TAG, "syncPlants size: ${result.size()}")
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
                        Log.d(LOG_TAG, "syncPlants: $plant")

                        CoroutineScope(Dispatchers.IO).launch {
                            appDatabase.PlantDao().delete(plant)
                            appDatabase.PlantDao().insert(plant = plant)
                            Log.d(LOG_TAG, "syncPlants: inserted")
                        }
                    }
                }
                .addOnFailureListener {
                    Log.d(LOG_TAG, "SyncPlantsWorker: $it")
                }
        }
    }
}
