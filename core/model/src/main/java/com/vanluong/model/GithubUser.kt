package com.vanluong.model

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

/**
 * Created by van.luong
 * on 18,April,2025
 */
@Parcelize
@JsonClass(generateAdapter = true)
data class GithubUser(
    @field:Json(name = "login") val login: String,
    @field:Json(name = "avatar_url") val avatarUrl: String,
    @field:Json(name = "html_url") val htmlUrl: String,
    @field:Json(name = "location") var location: String? = null,
    @field:Json(name = "followers") var followers: Int = 0,
    @field:Json(name = "following") var following: Int = 0,
) : Parcelable
