package com.vanluong.database

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.vanluong.database.entity.mapper.UserEntityMapper.toEntityList
import com.vanluong.testing.MockDataUtil
import junit.framework.TestCase.assertNotNull
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

/**
 * Created by van.luong
 * on 19,April,2025
 */
@RunWith(RobolectricTestRunner::class)
class GithubUserDatabaseTest {
    private lateinit var db: GithubUserDatabase
    private lateinit var dao: GithubUserDao

    @Before
    fun initDB() {
        val context = ApplicationProvider.getApplicationContext<Context>()

        db = Room.inMemoryDatabaseBuilder(context, GithubUserDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        dao = db.githubUserDao()
    }

    @After
    fun closeDB() {
        db.close()
    }

    @Test
    fun insertAndFetchUserTest() = runTest {
        val userEntityList = MockDataUtil.mockGithubUserList().toEntityList()
        dao.insertUsers(userEntityList)

        val fetchedUser = dao.getUserById(101)
        assertNotNull(fetchedUser)
        assertEquals(userEntityList[0].id, fetchedUser.id)
        assertEquals(userEntityList[0].login, fetchedUser.login)
    }

    @Test
    fun fetchPagedUsersTest() = runTest {
        val userEntityList = MockDataUtil.mockGithubUserList().toEntityList()
        dao.insertUsers(userEntityList)

        val pagedUsers = dao.getUsersPaged(limit = 5, offset = 0)
        assertEquals(2, pagedUsers.size)
        assertEquals("jvantuyl", pagedUsers[0].login)
        assertEquals("BrianTheCoder", pagedUsers[1].login)
    }

    @Test
    fun updateUserTest() = runTest {
        val userEntityList = MockDataUtil.mockGithubUserList().toEntityList()
        dao.insertUsers(userEntityList)

        val updatedUser = userEntityList[0].copy(location = "Mars", followers = 200)
        dao.updateUser(updatedUser)

        val fetchedUser = dao.getUserById(101)
        assertEquals("Mars", fetchedUser.location)
        assertEquals(200, fetchedUser.followers)
    }
}