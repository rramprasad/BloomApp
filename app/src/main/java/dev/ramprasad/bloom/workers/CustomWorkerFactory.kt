/*
 * Created by Ramprasad Ranganathan on 01/07/21, 7:58 PM
 * Copyright (c) 2021. All rights reserved
 * Last modified 01/07/21, 7:58 PM
 */

package dev.ramprasad.bloom.workers

import android.content.Context
import android.util.Log
import androidx.work.ListenableWorker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import com.google.firebase.firestore.FirebaseFirestore
import dev.ramprasad.bloom.database.AppDatabase

class CustomWorkerFactory( private val fireStoreDatabase: FirebaseFirestore, private val appDatabase: AppDatabase) : WorkerFactory() {
    override fun createWorker(
        appContext: Context,
        workerClassName: String,
        workerParameters: WorkerParameters
    ): ListenableWorker? {
        Log.d("worker", "createWorker: $workerClassName")
        return when(workerClassName){
            SyncPlantsWorker::class.java.name -> SyncPlantsWorker(appContext,workerParameters,fireStoreDatabase,appDatabase)
            SyncGardenThemesWorker::class.java.name -> SyncGardenThemesWorker(appContext,workerParameters,fireStoreDatabase,appDatabase)
            else -> null
        }
    }
}