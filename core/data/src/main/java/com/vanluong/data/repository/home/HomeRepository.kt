package com.vanluong.data.repository.home

import com.vanluong.common.Result
import com.vanluong.model.GithubUser
import kotlinx.coroutines.flow.Flow

/**
 * Created by van.luong
 * on 20,April,2025
 */
interface HomeRepository {
    fun fetchGithubUsers(since: Int, perPage: Int): Flow<Result<List<GithubUser>>>
}