package com.vanluong.network.service

import com.skydoves.sandwich.ApiResponse
import com.vanluong.model.GithubUser
import javax.inject.Inject

/**
 * Created by van.luong
 * on 18,April,2025
 */
class GithubClient @Inject constructor(
    private val githubService: GithubService
) {
    suspend fun getListUser(since: Int, perPage: Int): ApiResponse<List<GithubUser>> =
        githubService.getUsers(since, perPage)

    suspend fun getUserInfo(username: String): ApiResponse<GithubUser> =
        githubService.getUserInfo(username)
}

