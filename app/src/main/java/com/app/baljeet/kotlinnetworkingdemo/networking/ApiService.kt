package com.app.baljeet.kotlinnetworkingdemo.networking

import com.app.baljeet.kotlinnetworkingdemo.helpers.ApiConstants
import com.app.baljeet.kotlinnetworkingdemo.models.UserModel
import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiService {

    @GET("employees")
    fun getUsers(): Observable<ArrayList<UserModel>>

    companion object {
        fun create(): ApiService {
            val interceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
            val httpClient = OkHttpClient.Builder().addInterceptor(interceptor)
            val retrofit = Retrofit.Builder()
                .client(httpClient.build())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(ApiConstants.BASE_URL)
                .build()

            return retrofit.create(ApiService::class.java)
        }
    }
}