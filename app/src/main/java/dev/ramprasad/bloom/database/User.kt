/*
 * Created by Ramprasad Ranganathan on 02/06/21, 2:07 PM
 * Copyright (c) 2021. All rights reserved
 * Last modified 01/06/21, 2:45 PM
 */

package dev.ramprasad.bloom.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import javax.inject.Inject

@Entity(tableName = "user")
data class User @Inject constructor(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "user_id") val userId : Int,
    @ColumnInfo(name = "user_email") val userEmail : String,
    @ColumnInfo(name = "user_name") val userName : String)