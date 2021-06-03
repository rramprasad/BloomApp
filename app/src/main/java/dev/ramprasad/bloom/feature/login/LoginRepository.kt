/*
 * Created by Ramprasad Ranganathan on 02/06/21, 2:07 PM
 * Copyright (c) 2021. All rights reserved
 * Last modified 01/06/21, 2:45 PM
 */

package dev.ramprasad.bloom.feature.login

import com.google.firebase.auth.FirebaseAuth
import dev.ramprasad.bloom.database.AppDatabase
import dev.ramprasad.bloom.database.GardenTheme
import dev.ramprasad.bloom.database.Plant
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class LoginRepository @Inject constructor() {

    @Inject lateinit var firebaseAuth : FirebaseAuth

    suspend fun login(email: String, password: String): Flow<Boolean> {
        return flow {
            firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener { task ->
                if(task.isSuccessful){
                    flowOf(true)
                }
                else{
                    flowOf(false)
                }
            }
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
