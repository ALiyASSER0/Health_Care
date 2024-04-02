//package com.example.hospital.Data.network
//
//import com.example.hospital.Const
//import retrofit2.Retrofit
//import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
//import retrofit2.converter.gson.GsonConverterFactory
//
//
//object RetroConnect {
//
//     private val retrofit:Retrofit by lazy {
//        Retrofit.Builder().baseUrl(Const.BASE_URL)
//            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//    }
//
//        val apiService: RetrofitService by lazy {
//            retrofit.create(RetrofitService::class.java)
//        }
//
//
//
//}
//
