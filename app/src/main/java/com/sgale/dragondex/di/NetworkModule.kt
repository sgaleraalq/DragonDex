package com.sgale.dragondex.di

import com.sgale.dragondex.BuildConfig.BASE_URL
import com.sgale.dragondex.data.network.RepositoryImpl
import com.sgale.dragondex.data.network.services.DragonBallApiService
import com.sgale.dragondex.domain.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun provideDragonBallApiService(retrofit: Retrofit) = retrofit.create(DragonBallApiService::class.java)

    @Provides
    fun provideRepository(apiService: DragonBallApiService) = RepositoryImpl(apiService)

}