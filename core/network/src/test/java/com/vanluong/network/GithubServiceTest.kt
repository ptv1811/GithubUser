package com.vanluong.network

import com.skydoves.sandwich.ApiResponse
import com.vanluong.network.service.GithubService
import kotlinx.coroutines.test.runTest
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test
import java.io.IOException

/**
 * Created by van.luong
 * on 18,April,2025
 */
class GithubServiceTest : ApiAbstract<GithubService>() {
    private lateinit var service: GithubService

    @Before
    fun initService() {
        service = createService(GithubService::class.java)
    }

    @Throws(IOException::class)
    @Test
    fun fetchGithubUserListFromNetworkTest() = runTest {
        enqueueResponse("/UserListResponse.json")
        val response = service.getUsers(100, 20)
        val responseBody = requireNotNull((response as ApiResponse.Success).data)

        assertThat(responseBody.size, `is`(20))
        assertThat(responseBody[0].login, `is`("jvantuyl"))
        assertThat(
            responseBody[0].avatarUrl,
            `is`("https://avatars.githubusercontent.com/u/101?v=4")
        )
        assertThat(responseBody[0].htmlUrl, `is`("https://github.com/jvantuyl"))
    }

    @Throws(IOException::class)
    @Test
    fun fetchUserInfoFromNetworkTest() = runTest {
        enqueueResponse("/UserInfoResponse.json")
        val response = service.getUserInfo("jvantuyl")
        val responseBody = requireNotNull((response as ApiResponse.Success).data)

        assertThat(responseBody.login, `is`("jvantuyl"))
        assertThat(responseBody.avatarUrl, `is`("https://avatars.githubusercontent.com/u/101?v=4"))
        assertThat(responseBody.htmlUrl, `is`("https://github.com/jvantuyl"))
        assertThat(responseBody.location, `is`("Plumas County, California, USA"))
        assertThat(responseBody.followers, `is`(66))
        assertThat(responseBody.following, `is`(15))
    }
}