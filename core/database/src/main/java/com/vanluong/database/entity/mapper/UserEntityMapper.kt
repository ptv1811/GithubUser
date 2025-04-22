package com.vanluong.database.entity.mapper

import com.vanluong.database.entity.UserEntity
import com.vanluong.model.GithubUser

/**
 * Created by van.luong
 * on 19,April,2025
 *
 * Extension functions to map between [GithubUser] and [UserEntity].
 * because they are in separated modules.
 */
fun GithubUser.toEntity(): UserEntity {
    return UserEntity(
        id = this.id,
        login = this.login,
        avatarUrl = this.avatarUrl,
        htmlURL = this.htmlUrl,
        location = this.location,
        followers = this.followers,
        following = this.following
    )
}

fun UserEntity.toGithubUser(): GithubUser {
    return GithubUser(
        id = this.id,
        login = this.login,
        avatarUrl = this.avatarUrl,
        htmlUrl = this.htmlURL,
        location = this.location,
        followers = this.followers,
        following = this.following
    )
}

fun List<GithubUser>.toEntityList(): List<UserEntity> {
    return map { it.toEntity() }
}

fun List<UserEntity>.toDomainList(): List<GithubUser> {
    return map { it.toGithubUser() }
}
