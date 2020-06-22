package bh.yoo.mvpcoin.data

data class Coin(var page: Int, val coinData: ArrayList<CoinData>, var loadCount: Int = 0)