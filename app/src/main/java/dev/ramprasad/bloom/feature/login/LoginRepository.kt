/*
 * Created by Ramprasad Ranganathan on 29/06/21, 2:44 PM
 * Copyright (c) 2021. All rights reserved
 * Last modified 29/06/21, 2:44 PM
 */

package dev.ramprasad.bloom.feature.login

import androidx.startup.AppInitializer
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.google.firebase.auth.FirebaseAuth
import dev.ramprasad.bloom.workers.SyncGardenThemesWorker
import dev.ramprasad.bloom.workers.SyncPlantsWorker
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class LoginRepository @Inject constructor() {

    @Inject lateinit var firebaseAuth : FirebaseAuth
    //@Inject lateinit var bloomAppInitializer: AppInitializer
    //@Inject lateinit var workManager: WorkManager

    @ExperimentalCoroutinesApi
    suspend fun login(email: String, password: String): Flow<Boolean> {
        return callbackFlow {
            firebaseAuth.signInWithEmailAndPassword(email.trim(), password.trim())
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            //bloomAppInitializer.initializeComponent(BloomAppInitializer::class.java)
                            /*val constraints = Constraints.Builder()
                                .setRequiredNetworkType(NetworkType.CONNECTED)
                                .setRequiresBatteryNotLow(false)
                                .setRequiresCharging(false).build()

                            val syncGardenThemesWorkerRequest =
                                OneTimeWorkRequestBuilder<SyncGardenThemesWorker>()
                                    .setConstraints(constraints)
                                    .build()

                            val syncPlantsWorkerRequest =
                                OneTimeWorkRequestBuilder<SyncPlantsWorker>()
                                    .setConstraints(constraints)
                                    .build()
                            workManager
                                .beginWith(listOf(syncGardenThemesWorkerRequest,syncPlantsWorkerRequest))
                                .enqueue()*/
                            trySend(true)
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
}
