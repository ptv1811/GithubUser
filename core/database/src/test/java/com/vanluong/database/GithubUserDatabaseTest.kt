package com.vanluong.database

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.vanluong.database.entity.mapper.UserEntityMapper.toEntityList
import com.vanluong.testing.MockDataUtil
import kotlinx.coroutines.test.runTest
import org.junit.After
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

//        val pagingSource = dao.getUsersPaged()
//        val loadResult = pagingSource.load(
//            PagingSource.LoadParams.Refresh(
//                key = null,
//                loadSize = userEntityList.size,
//                placeholdersEnabled = false
//            )
//        )
//
//        assertTrue(loadResult is PagingSource.LoadResult.Page)
//        val page = loadResult as PagingSource.LoadResult.Page
//        assertEquals(userEntityList.size, page.data.size)
//        assertEquals(userEntityList, page.data)
    }
}