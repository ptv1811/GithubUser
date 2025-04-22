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
 *
 * This Hilt module provides the dependencies for the database layer.
 */
@Module
@InstallIn(SingletonComponent::class)
object PersistentModule {

    /**
     * Provides a singleton instance of [Moshi] for JSON serialization/deserialization.
     */
    @Provides
    @Singleton
    fun provideMoshi(): Moshi {
        return Moshi.Builder().build()
    }

    /**
     * Provides a singleton instance of [GithubUserDatabase] using Room.
     *
     * @param context The application context.
     */
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

    /**
     * Provides a singleton instance of [GithubUserDao] for accessing the database.
     *
     * @param githubUserDatabase The [GithubUserDatabase] instance.
     */
    @Provides
    @Singleton
    fun provideGithubUserDao(githubUserDatabase: GithubUserDatabase): GithubUserDao {
        return githubUserDatabase.githubUserDao()
    }
}