package com.vanluong.data.di

import com.vanluong.data.repository.details.UserDetailRepository
import com.vanluong.data.repository.details.UserDetailRepositoryImpl
import com.vanluong.data.repository.home.HomeRepository
import com.vanluong.data.repository.home.HomeRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

/**
 * Created by van.luong
 * on 20,April,2025
 *
 * This Hilt module provides the necessary dependencies for repositories.
 */
@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {
    /**
     * Provides an instance of HomeRepository.
     */
    @Provides
    @ViewModelScoped
    fun provideHomeRepository(impl: HomeRepositoryImpl): HomeRepository = impl

    /**
     * Provides an instance of UserDetailRepository.
     */
    @Provides
    @ViewModelScoped
    fun provideUserDetailRepository(impl: UserDetailRepositoryImpl): UserDetailRepository = impl
}