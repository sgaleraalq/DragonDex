package com.sgale.dragondex.di

import com.sgale.dragondex.BuildConfig.BASE_URL
import com.sgale.dragondex.data.core.interceptor.DbInterceptor
import com.sgale.dragondex.data.network.RepositoryImpl
import com.sgale.dragondex.data.network.services.DragonBallApiService
import com.sgale.dragondex.domain.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
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
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(dbInterceptor: DbInterceptor): OkHttpClient {
        val interceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

        return OkHttpClient
            .Builder()
            .addInterceptor(interceptor)
            .addInterceptor(dbInterceptor)
            .build()
    }

    @Provides
    fun provideDragonBallApiService(retrofit: Retrofit): DragonBallApiService {
        return retrofit.create(DragonBallApiService::class.java)
    }


    @Provides
    fun provideRepository(apiService: DragonBallApiService): Repository {
        return RepositoryImpl(apiService)
    }

}