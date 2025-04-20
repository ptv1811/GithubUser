package com.vanluong.home.adapter

import android.content.Context
import com.bumptech.glide.Glide
import com.vanluong.common.BaseRecyclerViewAdapter
import com.vanluong.home.R
import com.vanluong.home.databinding.UserItemBinding
import com.vanluong.model.GithubUser

/**
 * Created by van.luong
 * on 20,April,2025
 */
class GithubUserAdapter(val context: Context) :
    BaseRecyclerViewAdapter<GithubUser, UserItemBinding>(
        R.layout.user_item,
        onBindViewHolderCallback = { item, binding, _ ->
            binding.apply {
                tvUrl.text = item.htmlUrl
                tvName.text = item.login
                Glide.with(binding.root.context)
                    .load(item.avatarUrl)
//                .placeholder(R.drawable.ic_user_placeholder)
//                .error(R.drawable.ic_user_placeholder)
                    .into(ivAvatar)
            }
        }
    )