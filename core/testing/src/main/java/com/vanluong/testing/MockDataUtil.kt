package com.vanluong.testing

import com.vanluong.model.GithubUser

/**
 * Created by van.luong
 * on 19,April,2025
 *
 * Utility class to generate mock data for testing purposes.
 */
object MockDataUtil {
    fun mockGithubUserList(): List<GithubUser> {
        return listOf(
            GithubUser(
                101,
                "jvantuyl",
                "https://avatars.githubusercontent.com/u/101?v=4",
                "https://github.com/jvantuyl",
                "Plumas County, California, USA",
                66,
                15
            ),
            GithubUser(
                102,
                "BrianTheCoder",
                "https://avatars.githubusercontent.com/u/102?v=4",
                "https://github.com/BrianTheCoder",
                "Santa Monica,CA",
                105,
                32
            )
        )
    }
}