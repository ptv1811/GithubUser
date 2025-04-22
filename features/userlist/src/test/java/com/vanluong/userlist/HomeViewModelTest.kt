package com.vanluong.userlist

import app.cash.turbine.turbineScope
import com.vanluong.common.Result
import com.vanluong.data.repository.home.HomeRepository
import com.vanluong.model.GithubUser
import com.vanluong.testing.MainCoroutinesRule
import com.vanluong.testing.MockDataUtil
import com.vanluong.userlist.home.HomeViewModel
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.flowOf
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
class HomeViewModelTest {
    private lateinit var homeViewModel: HomeViewModel
    private var homeRepository: HomeRepository = mock()

    @get:Rule
    val coroutinesRule = MainCoroutinesRule()

    @Before
    fun setup() {
        homeViewModel = HomeViewModel(homeRepository)
    }

    @Test
    fun fetchGithubUserList() = runTest {
        val mockData = MockDataUtil.mockGithubUserList()
        whenever(homeRepository.fetchGithubUsers(0, 20)).thenReturn(
            flowOf(Result.Success(mockData))
        )

        turbineScope {
            val isLoadingFlow = homeViewModel.isLoading.testIn(backgroundScope)
            val userListFlow = homeViewModel.userList.testIn(backgroundScope)

            homeViewModel.fetchNextPage()

            // Initial value of flows
            val initialLoading = isLoadingFlow.awaitItem()
            println("Initial isLoading: $initialLoading")
            assertEquals(false, initialLoading)

            val initialUserList = userListFlow.awaitItem()
            println("Initial userList: $initialUserList")
            assertEquals(emptyList<GithubUser>(), initialUserList)

            // First emit
            val firstLoading = isLoadingFlow.awaitItem()
            println("First isLoading: $firstLoading")
            assertEquals(true, firstLoading)

            val updatedUserList = userListFlow.awaitItem()
            println("Updated userList: $updatedUserList")
            assertEquals(mockData, updatedUserList)

            // After emission, isLoading should be false
            val finalLoading = isLoadingFlow.awaitItem()
            println("Final isLoading: $finalLoading")
            assertEquals(false, finalLoading)
        }
    }
}