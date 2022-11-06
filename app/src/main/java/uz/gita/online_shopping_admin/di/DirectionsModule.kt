package uz.gita.online_shopping_admin.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.scopes.ViewModelScoped
import uz.gita.online_shopping_admin.directions.LoginScreenDirection
import uz.gita.online_shopping_admin.directions.MainScreenDirection
import uz.gita.online_shopping_admin.directions.OrderScreenDirection
import uz.gita.online_shopping_admin.directions.SplashScreenDirection
import uz.gita.online_shopping_admin.directions.impl.LoginScreenDirectionImpl
import uz.gita.online_shopping_admin.directions.impl.MainScreenDirectionImpl
import uz.gita.online_shopping_admin.directions.impl.OrderScreenDirectionImpl
import uz.gita.online_shopping_admin.directions.impl.SplashScreenDirectionImpl

// Created by Jamshid Isoqov an 11/5/2022
@Module
@InstallIn(ViewModelScoped::class)
interface DirectionsModule {

    @Binds
    fun bindSplashScreenDirections(impl: SplashScreenDirectionImpl): SplashScreenDirection

    @Binds
    fun bindsLoginScreenDirections(impl: LoginScreenDirectionImpl): LoginScreenDirection

    @Binds
    fun bindMainScreenDirections(impl: MainScreenDirectionImpl): MainScreenDirection

    @Binds
    fun bindOrderScreenDirections(impl: OrderScreenDirectionImpl): OrderScreenDirection


}