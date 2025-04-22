package com.vanluong.data

import app.cash.turbine.test
import com.skydoves.sandwich.ApiResponse
import com.vanluong.common.Result
import com.vanluong.data.repository.details.UserDetailRepository
import com.vanluong.data.repository.details.UserDetailRepositoryImpl
import com.vanluong.database.GithubUserDao
import com.vanluong.database.entity.mapper.toEntity
import com.vanluong.network.service.GithubClient
import com.vanluong.network.service.GithubService
import com.vanluong.testing.MainCoroutinesRule
import com.vanluong.testing.MockDataUtil
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever

/**
 * Created by van.luong
 * on 22,April,2025
 */
class UserDetailsRepositoryTest {
    private lateinit var userDetailRepository: UserDetailRepository
    private lateinit var githubUserClient: GithubClient

    private val githubService: GithubService = mock()
    private val githubUserDao: GithubUserDao = mock()

    @get:Rule
    val coroutinesRule = MainCoroutinesRule()

    @Before
    fun setup() {
        githubUserClient = GithubClient(githubService)
        userDetailRepository = UserDetailRepositoryImpl(githubUserClient, githubUserDao)
    }

    @Test
    fun `fetch user details from network test`() = runTest {
        val mockData = MockDataUtil.mockGithubUserList()[0]

        whenever(githubUserDao.getUserById(101)).thenReturn(null)
        whenever(githubService.getUserInfo("jvantuyl")).thenReturn(
            ApiResponse.of { mockData }
        )

        userDetailRepository.fetchUserDetail(mockData).test {
            // initial loading state
            assert(awaitItem() is Result.Loading)

            val expectItem = awaitItem()

            assert(expectItem is Result.Success)
            assertEquals(expectItem.data?.location, "Plumas County, California, USA")
            assertEquals(expectItem.data?.followers, 66)
            assertEquals(expectItem.data?.following, 15)
            awaitComplete()
        }
    }

    @Test
    fun `fetch user details from Room database test`() = runTest {
        val mockData = MockDataUtil.mockGithubUserList()[0]
        whenever(githubUserDao.getUserById(101)).thenReturn(
            mockData.toEntity().apply { hasDetails = true })

        userDetailRepository.fetchUserDetail(mockData).test {
            // Initial loading state
            assert(awaitItem() is Result.Loading)

            val expectItem = awaitItem()

            assert(expectItem is Result.Success)
            assertEquals(expectItem.data?.location, "Plumas County, California, USA")
            assertEquals(expectItem.data?.followers, 66)
            assertEquals(expectItem.data?.following, 15)
            awaitComplete()
        }
    }
}