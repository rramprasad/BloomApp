/*
 * Created by Ramprasad Ranganathan on 02/06/21, 2:07 PM
 * Copyright (c) 2021. All rights reserved
 * Last modified 01/06/21, 2:45 PM
 */

package dev.ramprasad.bloom.feature.login

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import dev.ramprasad.bloom.database.AppDatabase
import dev.ramprasad.bloom.database.GardenTheme
import dev.ramprasad.bloom.database.Plant
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class LoginRepository @Inject constructor() {

    @Inject lateinit var firebaseAuth : FirebaseAuth

    @ExperimentalCoroutinesApi
    suspend fun login(email: String, password: String): Flow<Boolean> {
        return callbackFlow {
            firebaseAuth.signInWithEmailAndPassword(email.trim(), password.trim())
                    .addOnCompleteListener { task ->
                        Log.d("compose", "on addOnCompleteListener ${task.isSuccessful}")
                        if (task.isSuccessful) {
                            trySend(true)
                        } else {
                            trySend(false)
                        }
                    }
            awaitClose()
        }
    }

    suspend fun isUserLoggedIn(): Boolean {
        firebaseAuth.currentUser?.run {
            return true
        } ?: run {
            return false
        }
    }
}
