package com.vanluong.data.repository.details

import com.vanluong.common.Result
import com.vanluong.model.GithubUser
import kotlinx.coroutines.flow.Flow

/**
 * Created by van.luong
 * on 21,April,2025
 */
interface UserDetailRepository {
    /**
     * Fetch user detail from Github API
     *
     * @param user the GithubUser instance
     * @return a flow of Result containing the GithubUser object
     */
    suspend fun fetchUserDetail(user: GithubUser): Flow<Result<GithubUser>>
}