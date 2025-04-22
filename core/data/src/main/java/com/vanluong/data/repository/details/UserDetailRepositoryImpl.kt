package com.vanluong.data.repository.details

import com.skydoves.sandwich.message
import com.skydoves.sandwich.suspendOnError
import com.skydoves.sandwich.suspendOnException
import com.skydoves.sandwich.suspendOnSuccess
import com.vanluong.common.Result
import com.vanluong.database.GithubUserDao
import com.vanluong.database.entity.mapper.toEntity
import com.vanluong.database.entity.mapper.toGithubUser
import com.vanluong.model.GithubUser
import com.vanluong.network.service.GithubClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

/**
 * Created by van.luong
 * on 21,April,2025
 */
class UserDetailRepositoryImpl @Inject constructor(
    private val githubClient: GithubClient,
    private val githubUserDao: GithubUserDao
) : UserDetailRepository {
    override suspend fun fetchUserDetail(
        user: GithubUser
    ): Flow<Result<GithubUser>> = flow<Result<GithubUser>> {
        emit(Result.Loading())

        // Check if the user is already in the database
        githubUserDao.getUserById(user.id)?.let { userEntity ->
            if (userEntity.hasDetails) {
                emit(Result.Success(userEntity.toGithubUser()))
                return@flow
            }
        }

        githubClient.getUserInfo(user.login)
            .suspendOnSuccess {
                user.apply {
                    location = data.location
                    followers = data.followers
                    following = data.following
                }
                githubUserDao.updateUser(user.toEntity().apply {
                    hasDetails = true
                })
                emit(Result.Success(user))
            }
            .suspendOnError {
                emit(Result.DataError(message()))
            }
            .suspendOnException {
                emit(Result.DataError(message()))
            }

    }.flowOn(Dispatchers.IO)
}