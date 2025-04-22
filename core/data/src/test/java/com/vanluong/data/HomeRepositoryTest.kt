package com.vanluong.data

import app.cash.turbine.test
import com.skydoves.sandwich.ApiResponse
import com.vanluong.common.Result
import com.vanluong.data.repository.home.HomeRepository
import com.vanluong.data.repository.home.HomeRepositoryImpl
import com.vanluong.database.GithubUserDao
import com.vanluong.database.entity.mapper.toEntityList
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
class HomeRepositoryTest {
    private lateinit var homeRepository: HomeRepository
    private lateinit var githubClient: GithubClient

    private val githubService: GithubService = mock()
    private val githubUserDao: GithubUserDao = mock()

    @get:Rule
    val coroutinesRule = MainCoroutinesRule()

    @Before
    fun setup() {
        githubClient = GithubClient(githubService)
        homeRepository = HomeRepositoryImpl(githubClient, githubUserDao)
    }

    @Test
    fun `fetch user from network test`() = runTest {
        val mockData = MockDataUtil.mockGithubUserList()

        whenever(githubUserDao.getUsersPaged(20, 0)).thenReturn(emptyList())
        whenever(githubService.getUsers(0, 20)).thenReturn(
            ApiResponse.of { mockData }
        )

        homeRepository.fetchGithubUsers(0, 20).test {
            val expectItem = awaitItem()

            assert(expectItem is Result.Success)
            assertEquals(expectItem.data?.size, 2)
            assertEquals(expectItem.data?.get(0)?.login, "jvantuyl")
            awaitComplete()
        }
    }

    @Test
    fun `fetch user from Room database test`() = runTest {
        val mockData = MockDataUtil.mockGithubUserList()
        whenever(githubUserDao.getUsersPaged(20, 0)).thenReturn(mockData.toEntityList())

        homeRepository.fetchGithubUsers(0, 20).test {
            val expectItem = awaitItem()

            assert(expectItem is Result.Success)
            assertEquals(expectItem.data?.size, 2)
            assertEquals(expectItem.data?.get(0)?.login, "jvantuyl")
            awaitComplete()
        }
    }
}