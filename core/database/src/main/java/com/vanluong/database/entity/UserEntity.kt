package com.vanluong.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by van.luong
 * on 19,April,2025
 */
@Entity
data class UserEntity(
    @PrimaryKey val id: Long,
    val login: String,
    val avatarUrl: String,
    val htmlURL: String,
    var location: String?,
    var followers: Int?,
    var following: Int?,

    var hasDetails: Boolean = false //  track if we've fetched details
)