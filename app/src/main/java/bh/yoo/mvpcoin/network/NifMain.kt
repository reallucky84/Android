package bh.yoo.mvpcoin.network

import bh.yoo.mvpcoin.data.Coin
import bh.yoo.mvpcoin.data.CoinData
import bh.yoo.mvpcoin.network.core.retrofit
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

class NifMain{

    fun api(): MainApi {
        return retrofit(MainApi::class.java)
    }

    interface MainApi{
        @GET("data.json")
        fun getData(@Query("startAt") startAt: Int, @Query("endAt") endAt : Int, @Query("orderBy") cmc_rank: String ="\"cmc_rank\"") : Call<ArrayList<CoinData>>
    }
}