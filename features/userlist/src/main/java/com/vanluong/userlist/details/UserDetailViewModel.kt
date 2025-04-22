package com.vanluong.userlist.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vanluong.common.Result
import com.vanluong.data.repository.details.UserDetailRepository
import com.vanluong.model.GithubUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by van.luong
 * on 21,April,2025
 */
@HiltViewModel
class UserDetailViewModel @Inject constructor(
    private val userDetailRepository: UserDetailRepository
) : ViewModel() {
    private val _user = MutableStateFlow<Result<GithubUser>?>(null)
    val user: StateFlow<Result<GithubUser>?> = _user

    fun fetchUserDetail(user: GithubUser) = viewModelScope.launch {
        userDetailRepository.fetchUserDetail(user)
            .collect { result ->
                _user.value = result
            }
    }
}