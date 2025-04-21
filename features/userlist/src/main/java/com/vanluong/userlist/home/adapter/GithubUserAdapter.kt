package com.vanluong.userlist.home.adapter

import android.content.Context
import com.bumptech.glide.Glide
import com.vanluong.common.BaseRecyclerViewAdapter
import com.vanluong.model.GithubUser
import com.vanluong.userlist.R
import com.vanluong.userlist.databinding.UserItemBinding
import com.vanluong.userlist.details.UserDetailActivity

/**
 * Created by van.luong
 * on 20,April,2025
 */
class GithubUserAdapter(private val context: Context) :
    BaseRecyclerViewAdapter<GithubUser, UserItemBinding>(
        R.layout.user_item,
        clickListener = { user, _ ->
            UserDetailActivity.startActivity(context, user)
        },
        onBindViewHolderCallback = { item, binding, _ ->
            binding.apply {
                tvUrl.text = item.htmlUrl
                tvName.text = item.login
                Glide.with(binding.root.context)
                    .load(item.avatarUrl)
                    .into(ivAvatar)
            }
        }
    )