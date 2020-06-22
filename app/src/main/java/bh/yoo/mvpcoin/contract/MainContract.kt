package bh.yoo.mvpcoin.contract

import androidx.annotation.StringRes
import bh.yoo.mvpcoin.data.Coin
import bh.yoo.mvpcoin.data.CoinData
import bh.yoo.mvpcoin.network.NifMain

class MainContract {


    interface View{
        fun getNumText(): String
        fun onGetData(result: ArrayList<CoinData>)
        fun onInputNumError(@StringRes resId: Int)
        fun onErrorGetData()
        fun enableButton()
        fun disableButton()
    }

    interface Presenter{
        fun getData(coin: Coin, loadCountText: String)
    }

    interface GetDataCallback {
        fun onGetData(result: ArrayList<CoinData>)
        fun onErrorGetConnectData()
    }

}

interface MainModel{
    fun getData(startAt: Int, endAt: Int, api: NifMain.MainApi, callback: MainContract.GetDataCallback)
}

