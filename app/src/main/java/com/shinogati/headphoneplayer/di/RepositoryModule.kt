package com.shinogati.headphoneplayer.di

import com.shinogati.headphoneplayer.data.repository.HeadphonePlayerRepositoryImp
import com.shinogati.headphoneplayer.domain.repository.HeadphonePlayerRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindHeadphonePlayerRepository(
        repositoryImp: HeadphonePlayerRepositoryImp
    ): HeadphonePlayerRepository
}