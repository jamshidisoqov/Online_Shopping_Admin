package uz.gita.online_shopping_admin.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.scopes.ViewModelScoped
import uz.gita.online_shopping_admin.directions.*
import uz.gita.online_shopping_admin.directions.impl.*

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

    @Binds
    fun bindClientScreenDirection(impl: ClientScreenDirectionImpl): ClientScreenDirection

    @Binds
    fun bindClientDetailScreenDirection(impl: ClientDetailDirectionImpl): ClientDetailDirection

    @Binds
    fun bindDriverScreenDirection(impl: DriverScreenDirectionImpl): DriverScreenDirection

    @Binds
    fun bindProductScreenDirection(impl: ProductsScreenDirectionImpl): ProductsScreenDirection

    @Binds
    fun bindSearchProductDirection(impl: SearchScreenDirectionImpl): SearchScreenDirection

    @Binds
    fun bindBranchesScreenDirection(impl: BranchesDirectionImpl): BranchesDirection

    @Binds
    fun bindDriverDetailsScreenDirection(impl: DriverDetailDirectionImpl): DriverDetailDirection


}