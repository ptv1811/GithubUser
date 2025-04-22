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
    /**
     * Insert a list of [UserEntity] into the database.
     *
     * @param userList the list of [UserEntity] to be inserted.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUsers(userList: List<UserEntity>)

    /**
     * Get a paged list of [UserEntity] from the database.
     *
     * @param limit the number of items to be fetched.
     * @param offset the offset for pagination.
     * @return a list of [UserEntity].
     */
    @Query("SELECT * FROM UserEntity ORDER BY id ASC LIMIT :limit OFFSET :offset")
    fun getUsersPaged(limit: Int, offset: Int): List<UserEntity>

    /**
     * Get a list of [UserEntity] from the database based on id.
     *
     * @param id the id of the user.
     * @return a list of [UserEntity].
     */
    @Query("SELECT * FROM UserEntity WHERE id = :id")
    fun getUserById(id: Long): UserEntity?

    /**
     * Update a [UserEntity]'s data in the database.
     *
     * @param userEntity the [UserEntity] to be updated.
     */
    @Update
    fun updateUser(userEntity: UserEntity)
}