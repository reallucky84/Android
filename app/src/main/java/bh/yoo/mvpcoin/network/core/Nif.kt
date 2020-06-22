package bh.yoo.mvpcoin.network.core

import bh.yoo.mvpcoin.data.Coin
import bh.yoo.mvpcoin.data.CoinData
import bh.yoo.mvpcoin.injector.BaseUrlInjector
import bh.yoo.mvpcoin.utils.CoinDeserializer
import com.google.gson.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Type


fun <T> retrofit(retClass: Class<T>, baseUrlInjector: BaseUrlInjector = BaseUrlInjector()): T {

    val httpClient = OkHttpClient.Builder()
    val logging = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
    // add logging as last interceptor
    httpClient.addInterceptor(logging)  // <-- this is the important line!


//    httpClient.addInterceptor { chain ->
//        val original = chain.request()
//        val request = original.newBuilder()
//        request.method(original.method, original.body)
//        chain.proceed(request.build())
//    }

    val gson = GsonBuilder()

    gson.serializeNulls()
    gson.registerTypeAdapter(ArrayList::class.java, CoinDeserializer(CoinData::class))

    return Retrofit.Builder().baseUrl(baseUrlInjector.getBaseUrl())
        .addConverterFactory(GsonConverterFactory.create(gson.create())).client(httpClient.build())
        .build()
        .create(retClass)

}

