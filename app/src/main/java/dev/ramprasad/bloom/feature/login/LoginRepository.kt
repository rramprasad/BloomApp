/*
 * Created by Ramprasad Ranganathan on 29/06/21, 2:44 PM
 * Copyright (c) 2021. All rights reserved
 * Last modified 29/06/21, 2:44 PM
 */

package dev.ramprasad.bloom.feature.login

import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class LoginRepository @Inject constructor() {

    @Inject lateinit var firebaseAuth : FirebaseAuth

    @ExperimentalCoroutinesApi
    suspend fun login(email: String, password: String): Flow<Boolean> {
        return callbackFlow {
            firebaseAuth.signInWithEmailAndPassword(email.trim(), password.trim())
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

    fun isUserLoggedIn(): Boolean {
        firebaseAuth.currentUser?.run {
            return true
        } ?: run {
            return false
        }
    }
}
