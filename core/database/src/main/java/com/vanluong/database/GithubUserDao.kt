package com.vanluong.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.vanluong.database.entity.UserEntity

/**
 * Created by van.luong
 * on 19,April,2025
 */
@Dao
interface GithubUserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUsers(userList: List<UserEntity>)

    @Query("SELECT * FROM UserEntity ORDER BY id ASC LIMIT :limit OFFSET :offset")
    fun getUsersPaged(limit: Int, offset: Int): List<UserEntity>

    @Query("SELECT * FROM UserEntity WHERE id = :id")
    fun getUserById(id: Long): UserEntity

    @Update
    fun updateUser(userEntity: UserEntity)
}