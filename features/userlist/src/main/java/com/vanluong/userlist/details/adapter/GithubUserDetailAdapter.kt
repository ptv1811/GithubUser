package com.vanluong.userlist.details.adapter

import com.vanluong.common.BaseRecyclerViewAdapter
import com.vanluong.userlist.R
import com.vanluong.userlist.databinding.UserDetailItemBinding

/**
 * Created by van.luong
 * on 21,April,2025
 */
sealed class UserDetailItem {
    data class Location(val location: String) : UserDetailItem()
    data class
    Followers(val count: String) : UserDetailItem()

    data class Following(val count: String) : UserDetailItem()
}

class GithubUserDetailAdapter : BaseRecyclerViewAdapter<UserDetailItem, UserDetailItemBinding>(
    R.layout.user_detail_item,
    onBindViewHolderCallback = { item, binding, _ ->
        binding.apply {
            when (item) {
                is UserDetailItem.Location -> {
                    // TODO: Use string resource
                    tvTitle.text = "Location"
                    tvDescription.text = item.location
                    ivIcon.setImageResource(R.drawable.ic_location)
                }

                is UserDetailItem.Followers -> {
                    tvTitle.text = "Followers"
                    tvDescription.text = item.count
                    ivIcon.setImageResource(R.drawable.ic_followers)
                }

                is UserDetailItem.Following -> {
                    tvTitle.text = "Following"
                    tvDescription.text = item.count
                    ivIcon.setImageResource(R.drawable.ic_following)
                }
            }
        }
    }
)