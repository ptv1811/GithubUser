package com.vanluong.userlist.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vanluong.data.repository.home.HomeRepository
import com.vanluong.model.GithubUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by van.luong
 * on 20,April,2025
 */
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: HomeRepository,
) : ViewModel() {
    private val _userList = MutableStateFlow<MutableList<GithubUser>>(mutableListOf())
    val userList: StateFlow<List<GithubUser>> = _userList

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private var since = 0
    private val pageSize = 20

    fun fetchNextPage() = viewModelScope.launch {
        if (_isLoading.value) {
            return@launch
        }
        _isLoading.value = true

        repository.fetchGithubUsers(since, pageSize)
            .onEach { result ->
                if (result.data == null) {
                    _isLoading.value = false
                    return@onEach
                }

                // We have to create new array list because Kotlin MutableStateFlow does not emit same reference of list
                val newUserList = ArrayList(_userList.value)
                newUserList.addAll(result.data!!)

                _userList.value = newUserList

                /*
                * We have to set since to the last user id because some of the Ids has been removed so it is not incremental
                 */
                since += result.data!!.last().id.toInt()
                _isLoading.value = false
            }
            .collect()
    }
}