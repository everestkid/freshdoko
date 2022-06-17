package com.teamonetech.freshdoko.di

import com.teamonetech.freshdoko.data.datasource.local.AppDao
import com.teamonetech.freshdoko.data.datasource.remote.RemoteDataSourceImpl
import com.teamonetech.freshdoko.data.repository.AppRepositoryImpl
import com.teamonetech.freshdoko.domain.repository.AppRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideAppRepository(appDao: AppDao): AppRepository {
        val remoteDataSourceImpl = RemoteDataSourceImpl()
        return AppRepositoryImpl(remoteDataSourceImpl, appDao)
    }
}