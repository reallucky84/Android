package bh.yoo.mvpcoin.injector

import bh.yoo.mvpcoin.network.NifMain

class ApiInjector {
    companion object{
        fun provideMainApi() : NifMain.MainApi{
            return NifMain().api()
        }
    }
}