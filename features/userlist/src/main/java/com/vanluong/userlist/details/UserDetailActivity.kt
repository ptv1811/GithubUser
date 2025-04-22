package com.vanluong.userlist.details

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.vanluong.common.BaseActivity
import com.vanluong.common.Result
import com.vanluong.model.GithubUser
import com.vanluong.userlist.R
import com.vanluong.userlist.databinding.ActivityUserDetailBinding
import com.vanluong.userlist.details.adapter.GithubUserDetailAdapter
import com.vanluong.userlist.details.adapter.UserDetailItem
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class UserDetailActivity : BaseActivity<ActivityUserDetailBinding>(R.layout.activity_user_detail) {
    private val userDetailViewModel: UserDetailViewModel by viewModels()
    private lateinit var githubUserDetailAdapter: GithubUserDetailAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        githubUserDetailAdapter = GithubUserDetailAdapter()

        binding {
            rvUserDetails.apply {
                setHasFixedSize(true)
                adapter = githubUserDetailAdapter
                layoutManager = LinearLayoutManager(this@UserDetailActivity)
            }
            mtDetails.setNavigationOnClickListener {
                finish()
            }
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                userDetailViewModel.user.collectLatest { result ->
                    when (result) {
                        is Result.Loading -> {
                            binding {
                                slUserDetail.apply {
                                    visibility = View.VISIBLE
                                    startShimmer()
                                }

                                svUserDetail.visibility = View.GONE
                            }
                        }

                        is Result.Success -> {
                            result.data?.let { user ->
                                binding.apply {
                                    tvUsername.text = user.login
                                    Glide.with(binding.root.context)
                                        .load(user.avatarUrl)
                                        .into(ivUserAvatar)

                                    githubUserDetailAdapter.setItems(mapUserToDetailItems(user))
                                    svUserDetail.visibility = View.VISIBLE
                                    slUserDetail.apply {
                                        stopShimmer()
                                        visibility = View.GONE
                                    }
                                }
                            }

                        }

                        is Result.DataError -> {
                            result.errorMessage?.let { error ->
                                AlertDialog.Builder(this@UserDetailActivity)
                                    .setTitle("Error")
                                    .setMessage(error)
                                    .setPositiveButton("OK") { dialog, _ ->
                                        dialog.dismiss()
                                        finish()
                                    }
                                    .show()
                            }
                        }

                        else -> {
                            // Do nothing
                        }
                    }
                }
            }
        }

        intent?.let { intent ->
            val user = intent.getParcelableExtra<GithubUser>(GithubUser::class.simpleName)
            user?.let {
                userDetailViewModel.fetchUserDetail(it)
            }
        }
    }

    private fun mapUserToDetailItems(user: GithubUser): List<UserDetailItem> {
        val list = mutableListOf<UserDetailItem>()
        list.add(UserDetailItem.Location(user.location ?: "N/A"))
        list.add(UserDetailItem.Followers(if (user.followers != null) user.followers.toString() else "N/A"))
        list.add(UserDetailItem.Following(if (user.following != null) user.following.toString() else "N/A"))
        return list
    }

    companion object {
        fun startActivity(context: Context, githubUser: GithubUser) {
            Intent(context, UserDetailActivity::class.java).apply {
                putExtra(GithubUser::class.simpleName, githubUser)
                context.startActivity(this)
            }
        }
    }
}