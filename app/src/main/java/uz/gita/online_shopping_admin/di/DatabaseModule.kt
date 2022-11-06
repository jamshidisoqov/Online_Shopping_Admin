package uz.gita.online_shopping_admin.di

import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import uz.gita.online_shopping_admin.data.local.prefs.MySharedPref
import javax.inject.Singleton

// Created by Jamshid Isoqov an 11/5/2022
@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    private const val SHARED_NAME = "app_data"

    @[Provides Singleton]
    fun provideSharedPreferences(@ApplicationContext ctx: Context): SharedPreferences =
        ctx.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE)

    @[Provides Singleton]
    fun provideMySharedPrefs(
        @ApplicationContext ctx: Context,
        sharedPreferences: SharedPreferences
    ): MySharedPref = MySharedPref(ctx, sharedPreferences)

}