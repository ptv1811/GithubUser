package com.vanluong.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.vanluong.database.entity.UserEntity

/**
 * Created by van.luong
 * on 19,April,2025
 */
@Database(
    entities = [UserEntity::class],
    version = 1,
    exportSchema = true
)
abstract class GithubUserDatabase : RoomDatabase() {
    abstract fun githubUserDao(): GithubUserDao
}