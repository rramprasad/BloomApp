package dev.ramprasad.bloom.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao {

    @Query("SELECT * FROM user")
    suspend fun getAll() : List<User>

    @Insert
    suspend fun insert(user: User)

    @Delete
    suspend fun delete(user: User)
}