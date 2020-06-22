package bh.yoo.mvpcoin.injector

import bh.yoo.mvpcoin.contract.MainModel
import bh.yoo.mvpcoin.model.MainModelImpl

class ModelInjector {
    companion object {
        fun provideMainModel(): MainModel {
            return MainModelImpl()
        }
    }
}