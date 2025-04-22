package com.vanluong.network.service

import com.skydoves.sandwich.ApiResponse
import com.vanluong.model.GithubUser
import javax.inject.Inject

/**
 * Created by van.luong
 * on 18,April,2025
 *
 * This class is responsible for making network requests to the Github API.
 */
class GithubClient @Inject constructor(
    private val githubService: GithubService
) {
    /**
     * Fetches a paged list of Github users from the API.
     *
     * @param since The ID of the last user received in the previous response.
     * @param perPage The number of users to return per page.
     * @return An ApiResponse containing a list of GithubUser objects.
     */
    suspend fun getListUser(since: Int, perPage: Int): ApiResponse<List<GithubUser>> =
        githubService.getUsers(since, perPage)

    /**
     * Fetches detailed information about a specific Github user.
     *
     * @param username The login name of the user.
     * @return An ApiResponse containing a GithubUser object with detailed information.
     */
    suspend fun getUserInfo(username: String): ApiResponse<GithubUser> =
        githubService.getUserInfo(username)
}

