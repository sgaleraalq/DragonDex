package com.sgale.dragondex.di

import android.content.Context
import androidx.room.Room
import com.sgale.dragondex.data.database.CharactersDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    private const val CHARACTER_DATABASE_NAME = "character_database"

    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context) = Room.databaseBuilder(
        context = context,
        klass = CharactersDatabase::class.java,
        name = CHARACTER_DATABASE_NAME
    ).build()


    @Singleton
    @Provides
    fun provideCharacterDao(database: CharactersDatabase) = database.getCharacterDao()
}