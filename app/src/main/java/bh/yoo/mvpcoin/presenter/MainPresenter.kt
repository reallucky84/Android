package bh.yoo.mvpcoin.presenter

import bh.yoo.mvpcoin.R
import bh.yoo.mvpcoin.contract.MainContract
import bh.yoo.mvpcoin.contract.MainModel
import bh.yoo.mvpcoin.data.Coin
import bh.yoo.mvpcoin.data.CoinData
import bh.yoo.mvpcoin.injector.ApiInjector
import bh.yoo.mvpcoin.model.MainModelImpl

class MainPresenter(val view: MainContract.View, val mainModel: MainModel) : MainContract.Presenter {

    override fun getData(coin: Coin, loadCountText: String) {
        view.disableButton()

        try {
            val loadCount = loadCountText.toInt()

            if (loadCount > 0) {
                if(coin.loadCount != loadCount){
                    coin.page = 0
                    coin.loadCount = loadCount
                }

                val startAt = coin.page * loadCount + 1
                val endAt = startAt * loadCount

                mainModel.getData(startAt, endAt, ApiInjector.provideMainApi(), object : MainContract.GetDataCallback {
                    override fun onGetData(result: ArrayList<CoinData>) {
                        result.sort()
                        view.onGetData(result)
                    }

                    override fun onErrorGetConnectData() {
                        view.onErrorGetData()
                    }
                })

            } else {
                view.onInputNumError(R.string.number_zero)
            }

        } catch (e: NumberFormatException) {
            e.printStackTrace()
            view.onInputNumError(R.string.number_wrong)
        }

        view.enableButton()
    }


}
