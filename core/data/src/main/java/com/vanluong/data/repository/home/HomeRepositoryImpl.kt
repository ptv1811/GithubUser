package com.vanluong.data.repository.home

import com.skydoves.sandwich.message
import com.skydoves.sandwich.suspendOnError
import com.skydoves.sandwich.suspendOnException
import com.skydoves.sandwich.suspendOnSuccess
import com.vanluong.common.Result
import com.vanluong.database.GithubUserDao
import com.vanluong.database.entity.mapper.UserEntityMapper.toDomainList
import com.vanluong.database.entity.mapper.UserEntityMapper.toEntityList
import com.vanluong.model.GithubUser
import com.vanluong.network.service.GithubClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

/**
 * Created by van.luong
 * on 20,April,2025
 */
class HomeRepositoryImpl @Inject constructor(
    private val githubClient: GithubClient,
    private val githubUserDao: GithubUserDao
) : HomeRepository {
    override fun fetchGithubUsers(
        since: Int,
        perPage: Int
    ): Flow<Result<List<GithubUser>>> = flow<Result<List<GithubUser>>> {
        val githubUser = githubUserDao.getUsersPaged(perPage, since)

        if (githubUser.isNotEmpty()) {
            emit(Result.Success(githubUser.toDomainList()))
        } else {
            githubClient.getListUser(since, perPage)
                .suspendOnSuccess {
                    githubUserDao.insertUsers(data.toEntityList())
                    emit(Result.Success(data))
                }
                .suspendOnError {
                    emit(Result.DataError(message()))
                }
                .suspendOnException {
                    emit(Result.DataError(message()))
                }
        }
    }.flowOn(Dispatchers.IO)
}