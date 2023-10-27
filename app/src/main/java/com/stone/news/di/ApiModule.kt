package com.stone.news.di

import android.content.Context
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.stone.news.BuildConfig
import com.stone.news.data.remote.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
class ApiModule {
    @Provides
    fun providesHeaderInterceptor() : Interceptor {
        return Interceptor { chain ->
            val request = chain.request()

            val newRequest = request.newBuilder()
                .addHeader("accept","application/json")
                .addHeader("content-type","application/json")
                .addHeader("Authorization", "Bearer ${BuildConfig.API_KEY}")
                .method(request.method, request.body)
                .build()

            chain.proceed(newRequest)
        }
    }

    @Provides
    fun providesOkhttp(
        @ApplicationContext context : Context,
        interceptor : Interceptor
    ) : OkHttpClient {
        val connectTimeout = 60L
        val readTimeout = 600L
        val writeTimeout = 600L

        val builder = OkHttpClient.Builder()
            .connectTimeout(connectTimeout, TimeUnit.SECONDS)
            .readTimeout(readTimeout, TimeUnit.SECONDS)
            .writeTimeout(writeTimeout, TimeUnit.SECONDS)
            .cache(null)
            .retryOnConnectionFailure(true)

        builder.addInterceptor(interceptor)


        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        builder.interceptors().add(logging)

        return builder.build()
    }

    @Provides
    fun providesMoshi() : Moshi {
        return Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    @Provides
    fun providesRetrofit(okHttpClient : OkHttpClient, moshi : Moshi) : Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://newsapi.org/")
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi).asLenient())
            .build()
    }

    @Provides
    fun providesApiService(retrofit : Retrofit) : ApiService {
        return retrofit.create(ApiService::class.java)
    }

}