package com.vanluong.data.repository.home

import com.vanluong.common.Result
import com.vanluong.model.GithubUser
import kotlinx.coroutines.flow.Flow

/**
 * Created by van.luong
 * on 20,April,2025
 *
 * This interface is used to fetch Github Users from the remote data source.
 */
interface HomeRepository {
    /**
     * Fetches a list of Github users from the remote data source.
     *
     * @param since The starting point for fetching users.
     * @param perPage The number of users to fetch per page.
     * @return A Flow that emits the result of the fetch operation.
     */
    fun fetchGithubUsers(since: Int, perPage: Int): Flow<Result<List<GithubUser>>>
}