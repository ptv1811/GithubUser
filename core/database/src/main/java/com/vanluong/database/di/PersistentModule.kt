package com.vanluong.database.di

import android.content.Context
import androidx.room.Room
import com.squareup.moshi.Moshi
import com.vanluong.database.GithubUserDao
import com.vanluong.database.GithubUserDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by van.luong
 * on 19,April,2025
 */
@Module
@InstallIn(SingletonComponent::class)
object PersistentModule {
    @Provides
    @Singleton
    fun provideMoshi(): Moshi {
        return Moshi.Builder().build()
    }

    @Provides
    @Singleton
    fun provideGithubUserDatabase(
        @ApplicationContext context: Context,
    ): GithubUserDatabase {
        return Room
            .databaseBuilder(context, GithubUserDatabase::class.java, "GithubUser.db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideGithubUserDao(githubUserDatabase: GithubUserDatabase): GithubUserDao {
        return githubUserDatabase.githubUserDao()
    }
}