/*
 * Designed and developed by 2025 sgaleraalq (Sergio Galera)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.sgale.dragondex.di

import android.content.Context
import androidx.room.Room
import com.sgale.dragondex.data.database.db.CharactersDatabase
import com.sgale.dragondex.data.database.db.PlanetsDatabase
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
    private const val PLANETS_DATABASE_NAME = "planets_database"

    @Singleton
    @Provides
    fun provideCharactersDb(@ApplicationContext context: Context) =
        Room.databaseBuilder(
            context = context,
            klass = CharactersDatabase::class.java,
            name = CHARACTER_DATABASE_NAME
        ).build()

    @Singleton
    @Provides
    fun providePlanetsDb(@ApplicationContext context: Context) =
        Room.databaseBuilder(
            context = context,
            klass = PlanetsDatabase::class.java,
            name = PLANETS_DATABASE_NAME
        ).build()


    @Singleton
    @Provides
    fun provideCharacterDao(database: CharactersDatabase) = database.getCharacterDao()

    @Singleton
    @Provides
    fun providePlanetsDao(database: PlanetsDatabase) = database.getPlanetsDao()
}