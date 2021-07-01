/*
 * Created by Ramprasad Ranganathan on 02/06/21, 2:07 PM
 * Copyright (c) 2021. All rights reserved
 * Last modified 01/06/21, 2:45 PM
 */

package dev.ramprasad.bloom

import android.app.Application
import android.util.Log
import androidx.hilt.work.HiltWorkerFactory
import androidx.startup.AppInitializer
import androidx.work.*
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.HiltAndroidApp
import dev.ramprasad.bloom.appinitializer.BloomAppInitializer
import dev.ramprasad.bloom.database.AppDatabase
import dev.ramprasad.bloom.workers.CustomWorkerFactory
import dev.ramprasad.bloom.workers.SyncGardenThemesWorker
import dev.ramprasad.bloom.workers.SyncPlantsWorker
import javax.inject.Inject

@HiltAndroidApp
class BloomApp : Application()/*, Configuration.Provider*/{

    //@Inject lateinit var workerFactory: HiltWorkerFactory
    @Inject lateinit var fireStoreDatabase: FirebaseFirestore
    @Inject lateinit var appDatabase: AppDatabase

    override fun onCreate() {
        super.onCreate()
        //AppInitializer.getInstance(this).initializeComponent(BloomAppInitializer::class.java)
    }

    /*override fun getWorkManagerConfiguration(): Configuration {
        val delegatingWorkerFactory = DelegatingWorkerFactory()
        delegatingWorkerFactory.addFactory(CustomWorkerFactory(fireStoreDatabase,appDatabase))
        return Configuration.Builder()
            .setWorkerFactory(delegatingWorkerFactory)
            .setMinimumLoggingLevel(Log.DEBUG)
            .build()
    }*/
}


