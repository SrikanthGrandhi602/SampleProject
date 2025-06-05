package com.example.sampleproject.di

import com.example.sampleproject.model.repo.FetchService
import com.example.sampleproject.model.repo.FetchServiceImpl
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
    abstract fun provideRepo(fetchServiceImpl: FetchServiceImpl) : FetchService

}