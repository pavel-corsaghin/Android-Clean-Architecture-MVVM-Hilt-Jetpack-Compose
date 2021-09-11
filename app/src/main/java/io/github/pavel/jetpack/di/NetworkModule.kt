package io.github.pavel.jetpack.di

import io.github.pavel.jetpack.data.service.NewsService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Named("BaseUrl")
    fun provideBaseUrl() = "https://example.org/v2/" // Todo: config in build gradle

    @Provides
    fun provideRetrofit(
        @Named("BaseUrl") baseUrl: String,
        okHttpClient: OkHttpClient,
    ): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .baseUrl(baseUrl)
            .build()
    }

    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .addInterceptor {
                val original = it.request()
                val newRequestBuilder = original.newBuilder()

                newRequestBuilder.addHeader("Content-Type", "application/json")
                newRequestBuilder.addHeader("Accept", "application/json")
                newRequestBuilder.addHeader("Authorization", "Bearer " + "token") //Todo: Add token

                it.proceed(newRequestBuilder.build())
            }
            .addInterceptor {
                val original = it.request()
                val newRequestBuilder = original.newBuilder()

                // Todo: add other interceptor handle like retry, rewrite etc here

                it.proceed(newRequestBuilder.build())
            }
            .callTimeout(1, TimeUnit.MINUTES)
            .readTimeout(1, TimeUnit.MINUTES)
            .writeTimeout(1, TimeUnit.MINUTES)
            .build()
    }

    @Provides
    fun providerNewsService(retrofit: Retrofit): NewsService =
        retrofit.create(NewsService::class.java)
}