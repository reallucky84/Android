package bh.yoo.mvpcoin.injector

import bh.yoo.mvpcoin.contract.MainModel
import bh.yoo.mvpcoin.model.MainModelImpl

class BaseUrlInjector {
    fun getBaseUrl(): String {
        return "https://fir-test-fb8d1.firebaseio.com/"
    }
}