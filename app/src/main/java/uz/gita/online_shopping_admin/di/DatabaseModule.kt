package uz.gita.online_shopping_admin.di

import android.content.Context
import android.content.SharedPreferences
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import uz.gita.online_shopping_admin.data.local.prefs.MySharedPref
import uz.gita.online_shopping_admin.data.remote.MaxWayApi
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

// Created by Jamshid Isoqov an 11/5/2022
@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    private const val SHARED_NAME = "app_data"

    private const val BASE_URL = "https://9023-37-110-211-231.ap.ngrok.io"

    private const val CONNECTION_TIME_OUT = 5000L


    @[Provides Singleton]
    fun provideGson(): Gson = GsonBuilder().setLenient().create()

    @[Provides Singleton]
    fun provideClient(
        @ApplicationContext context: Context,
        mySharedPref:MySharedPref
    ): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(ChuckerInterceptor.Builder(context).build())
            .connectTimeout(CONNECTION_TIME_OUT, TimeUnit.MILLISECONDS)
            .addInterceptor { chain ->
                val requestBuilder = chain.request().newBuilder()
                if (mySharedPref.token.isNotEmpty())
                    requestBuilder.addHeader("Authorization", "Bearer ${mySharedPref.token}")
                chain.proceed(requestBuilder.build())
            }
            .readTimeout(CONNECTION_TIME_OUT, TimeUnit.MILLISECONDS)
            .build()

    @[Provides Singleton Named(value = "mainApi")]
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        gson: Gson
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()

    @[Provides Singleton]
    fun provideMaxWayApi(@Named("mainApi") retrofit: Retrofit): MaxWayApi =
        retrofit.create(MaxWayApi::class.java)


    @[Provides Singleton]
    fun provideSharedPreferences(@ApplicationContext ctx: Context): SharedPreferences =
        ctx.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE)

    @[Provides Singleton]
    fun provideMySharedPrefs(
        @ApplicationContext ctx: Context,
        sharedPreferences: SharedPreferences
    ): MySharedPref = MySharedPref(ctx, sharedPreferences)




}