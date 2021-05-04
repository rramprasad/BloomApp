package dev.ramprasad.bloom.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class User(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "user_id") val userId : Int,
    @ColumnInfo(name = "user_email") val userEmail : String,
    @ColumnInfo(name = "user_name") val userName : String)