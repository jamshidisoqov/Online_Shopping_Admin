package uz.gita.online_shopping_admin.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.gita.online_shopping_admin.repository.Repository
import uz.gita.online_shopping_admin.repository.impl.RepositoryImpl

// Created by Jamshid Isoqov an 11/5/2022
@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun bindsRepository(impl: RepositoryImpl): Repository

}