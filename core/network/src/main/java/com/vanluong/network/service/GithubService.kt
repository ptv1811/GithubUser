package com.vanluong.network.service

import com.skydoves.sandwich.ApiResponse
import com.vanluong.model.GithubUser
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by van.luong
 * on 18,April,2025
 */
interface GithubService {
    /**
     * Get a list of users from the Github API.
     * @param since The ID of the last user received in the previous response.
     * @param perPage The number of users to return per page.
     */
    @GET("users")
    suspend fun getUsers(
        @Query("since") since: Int,
        @Query("per_page") perPage: Int
    ): ApiResponse<List<GithubUser>>

    /**
     * Get information about a specific user from the Github API.
     * @param loginName The login name of the user.
     */
    @GET("users/{login_name}")
    suspend fun getUserInfo(
        @Path("login_name") loginName: String
    ): ApiResponse<GithubUser>
}