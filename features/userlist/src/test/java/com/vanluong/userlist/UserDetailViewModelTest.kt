package com.vanluong.userlist

import app.cash.turbine.test
import com.vanluong.common.Result
import com.vanluong.data.repository.details.UserDetailRepository
import com.vanluong.testing.MainCoroutinesRule
import com.vanluong.testing.MockDataUtil
import com.vanluong.userlist.details.UserDetailViewModel
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
class UserDetailViewModelTest {
    private lateinit var userDetailViewModel: UserDetailViewModel
    private var userDetailRepository: UserDetailRepository = mock()

    @get:Rule
    val coroutinesRule = MainCoroutinesRule()

    @Before
    fun setup() {
        userDetailViewModel = UserDetailViewModel(userDetailRepository)
    }

    @Test
    fun fetchUserDetail() = runTest {
        val mockData = MockDataUtil.mockGithubUserList()[0]
        whenever(userDetailRepository.fetchUserDetail(mockData)).thenReturn(
            flowOf(Result.Success(mockData))
        )

        userDetailViewModel.user.test {
            userDetailViewModel.fetchUserDetail(mockData)

            // Initial state is null
            assertEquals(null, awaitItem())

            val successItem = awaitItem()
            assert(successItem is Result.Success<*>)
            if (successItem != null) {
                assertEquals(successItem.data, mockData)
            }
        }
    }
}