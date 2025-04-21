package com.vanluong.userlist.home

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vanluong.common.BaseActivity
import com.vanluong.userlist.R
import com.vanluong.userlist.databinding.ActivityHomeBinding
import com.vanluong.userlist.home.adapter.GithubUserAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeActivity : BaseActivity<ActivityHomeBinding>(R.layout.activity_home) {
    private val homeViewModel: HomeViewModel by viewModels()
    private lateinit var githubUserAdapter: GithubUserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        githubUserAdapter = GithubUserAdapter(this)
        setSupportActionBar(binding.toolbar)

        binding {
            rvUsers.apply {
                setHasFixedSize(true)
                adapter = githubUserAdapter

                addOnScrollListener(object : RecyclerView.OnScrollListener() {
                    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                        super.onScrolled(recyclerView, dx, dy)
                        val totalItemCount = (layoutManager as LinearLayoutManager).itemCount
                        val lastVisibleItem =
                            (layoutManager as LinearLayoutManager).findLastVisibleItemPosition()

                        if (!homeViewModel.isLoading.value && lastVisibleItem + 5 >= totalItemCount) {
                            homeViewModel.fetchNextPage()
                        }
                    }
                })
            }
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                homeViewModel.userList.collectLatest { userList ->
                    githubUserAdapter.setItems(userList)
                }
            }
        }

        homeViewModel.fetchNextPage()
    }
}