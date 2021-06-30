/*
 * Created by Ramprasad Ranganathan on 30/06/21, 5:10 PM
 * Copyright (c) 2021. All rights reserved
 * Last modified 30/06/21, 5:10 PM
 */

package dev.ramprasad.bloom

import android.content.Context
import androidx.startup.Initializer
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import dev.ramprasad.bloom.workers.SyncGardenThemesWorker
import dev.ramprasad.bloom.workers.SyncPlantsWorker

class BloomAppInitializer : Initializer<WorkManager> {

    override fun create(context: Context): WorkManager {
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
}