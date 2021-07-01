/*
 * Created by Ramprasad Ranganathan on 01/07/21, 4:42 PM
 * Copyright (c) 2021. All rights reserved
 * Last modified 01/07/21, 3:07 PM
 */

package dev.ramprasad.bloom.appinitializer

import android.content.Context
import android.util.Log
import androidx.hilt.work.HiltWorkerFactory
import androidx.startup.Initializer
import androidx.work.*
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.EntryPointAccessors
import dagger.hilt.components.SingletonComponent
import dev.ramprasad.bloom.workers.SyncGardenThemesWorker
import dev.ramprasad.bloom.workers.SyncPlantsWorker

class BloomAppInitializer : Initializer<WorkManager> {

    override fun create(context: Context): WorkManager {
        Log.d("BloomAppInitializer", "create: ")

        val workerFactory = getWorkerFactory(appContext = context.applicationContext)

        val config = Configuration.Builder()
            .setWorkerFactory(workerFactory)
            .build()
        WorkManager.initialize(context, config)

        val workManager = WorkManager.getInstance(context)

        val constraints = Constraints.Builder()
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
            .enqueue()
        return workManager
    }

    override fun dependencies(): List<Class<out Initializer<*>>> {
        return emptyList()
    }

    private fun getWorkerFactory(appContext: Context): HiltWorkerFactory {
        val workManagerEntryPoint = EntryPointAccessors.fromApplication(
            appContext,
            WorkManagerInitializerEntryPoint::class.java
        )
        return workManagerEntryPoint.hiltWorkerFactory()
    }

    @InstallIn(SingletonComponent::class)
    @EntryPoint
    interface WorkManagerInitializerEntryPoint {
        fun hiltWorkerFactory(): HiltWorkerFactory
    }
}
