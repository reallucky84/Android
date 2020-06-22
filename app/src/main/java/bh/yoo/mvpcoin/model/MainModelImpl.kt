package bh.yoo.mvpcoin.model

import bh.yoo.mvpcoin.contract.MainContract
import bh.yoo.mvpcoin.contract.MainModel
import bh.yoo.mvpcoin.data.CoinData
import bh.yoo.mvpcoin.network.NifMain
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainModelImpl : MainModel {

    override fun getData(startAt: Int, endAt: Int, api: NifMain.MainApi, callback: MainContract.GetDataCallback) {

        api.getData(startAt, endAt).enqueue(object : Callback<ArrayList<CoinData>> {
            override fun onResponse(call: Call<ArrayList<CoinData>>, response: Response<ArrayList<CoinData>>) {
                if (response.isSuccessful && response.body() != null) {
                    callback.onGetData(response.body()!!)
                } else {
                    callback.onErrorGetConnectData()
                }

            }

            override fun onFailure(call: Call<ArrayList<CoinData>>, t: Throwable) {
                t.printStackTrace()
                callback.onErrorGetConnectData()
            }

        })
    }


}