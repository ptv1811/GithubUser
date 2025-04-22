package com.vanluong.userlist.home

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.View
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

        binding {
            slHome.apply {
                startShimmer()
                visibility = View.VISIBLE
            }

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
                            Log.d("TAG", "onScrolled: $lastVisibleItem")
                            Log.d("TAG", "onScrolled: $totalItemCount")
                            homeViewModel.fetchNextPage()
                        }
                    }
                })

                visibility = View.GONE
            }
        }

        lifecycleScope.launch {
            launch {
                repeatOnLifecycle(Lifecycle.State.STARTED) {
                    homeViewModel.userList.collectLatest { userList ->
                        githubUserAdapter.setItems(userList)
                        binding {
                            rvUsers.visibility = View.VISIBLE
                            slHome.apply {
                                stopShimmer()
                                visibility = View.GONE
                            }
                        }
                    }
                }
            }

            launch {
                repeatOnLifecycle(Lifecycle.State.STARTED) {
                    homeViewModel.errorMessage.collectLatest { error ->
                        AlertDialog.Builder(this@HomeActivity)
                            .setTitle("Error")
                            .setMessage(error)
                            .setPositiveButton("OK") { dialog, _ ->
                                dialog.dismiss()
                            }
                            .show()
                    }
                }
            }
        }

        homeViewModel.fetchNextPage()
    }
}