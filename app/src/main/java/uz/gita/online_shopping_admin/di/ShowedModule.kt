package uz.gita.online_shopping_admin.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.gita.online_shopping_admin.showed.Showed
import uz.gita.online_shopping_admin.showed.impl.ShowedImpl
import javax.inject.Singleton

// Created by Jamshid Isoqov an 11/5/2022
@Module
@InstallIn(SingletonComponent::class)
interface ShowedModule {

    @[Provides Singleton]
    fun bindShowed(impl: ShowedImpl): Showed

}