package com.vanluong.network.di

import com.skydoves.sandwich.retrofit.adapters.ApiResponseCallAdapterFactory
import com.vanluong.network.interceptor.HttpRequestInterceptor
import com.vanluong.network.service.GithubClient
import com.vanluong.network.service.GithubService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

/**
 * Created by van.luong
 * on 18,April,2025
 */
@Module
@InstallIn(SingletonComponent::class)
internal object NetworkModule {

    /**
     * Provides an instance of OkHttpClient.
     * It should be a Singleton
     */
    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpRequestInterceptor())
            .build()
    }

    /**
     * Provides an instance of Retrofit.
     * It should be a Singleton
     */
    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(ApiResponseCallAdapterFactory.create())
            .build()
    }

    /**
     * Provides an instance of GithubService.
     * It should be a Singleton
     */
    @Provides
    @Singleton
    fun provideGithubService(retrofit: Retrofit): GithubService {
        return retrofit.create(GithubService::class.java)
    }

    /**
     * Provides an instance of GithubClient.
     * It should be a Singleton
     */
    @Provides
    @Singleton
    fun provideGithubClient(githubService: GithubService): GithubClient {
        return GithubClient(githubService)
    }
}