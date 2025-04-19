package com.vanluong.database.entity.mapper

import com.vanluong.database.entity.UserEntity
import com.vanluong.model.GithubUser

/**
 * Created by van.luong
 * on 19,April,2025
 */
object UserEntityMapper {
    fun fromGithubUserToEntity(user: GithubUser): UserEntity {
        return UserEntity(
            id = user.id,
            login = user.login,
            avatarUrl = user.avatarUrl,
            htmlURL = user.htmlUrl,
            location = user.location,
            followers = user.followers,
            following = user.following
        )
    }

    fun fromEntityToGithubUser(entity: UserEntity): GithubUser {
        return GithubUser(
            id = entity.id,
            login = entity.login,
            avatarUrl = entity.avatarUrl,
            htmlUrl = entity.htmlURL,
            location = entity.location,
            followers = entity.followers,
            following = entity.following
        )
    }
}