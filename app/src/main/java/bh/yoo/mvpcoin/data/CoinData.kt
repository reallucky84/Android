package bh.yoo.mvpcoin.data

data class CoinData(val circulating_supply: Double,
                    val cmc_rank: Int,
                    val date_added: String,
                    val id: Int,
                    val last_updated: String,
                    val max_supply: String,
                    val name: String,
                    val num_market_pairs: Int,
                    val slug: String,
                    val symbol: String,
                    val total_supply: String,
                    val tags: ArrayList<String>,
                    val quote: Quote.USDdata
): Comparable<CoinData>{

    data class Quote(val USD: USDdata){
        data class USDdata(val last_updated: String,
                       val market_cap:Double,
                       val percent_change_1h: Double,
                       val percent_change_24h: Double,
                       val percent_change_7d: Double,
                       val price: Double,
                       val volume_24h: Double)
    }

    override fun compareTo(other: CoinData): Int {
        if(this.cmc_rank < other.cmc_rank){
            return -1
        } else if(this.cmc_rank > other.cmc_rank){
            return 1
        }
        return 0
    }

}


//{
//    circulating_supply: 17906012,
//    cmc_rank: 1,
//    date_added: "2013-04-28T00:00:00.000Z",
//    id: 1,
//    last_updated: "2019-08-30T18:51:28.000Z",
//    max_supply: 21000000,
//    name: "Bitcoin",
//    num_market_pairs: 7919,
//    quote: {
//    USD: {
//        last_updated: "2019-08-30T18:51:28.000Z",
//        market_cap: 171155540318.86005,
//        percent_change_1h: -0.127291,
//        percent_change_24h: 0.328918,
//        percent_change_7d: -8.00576,
//        price: 9558.55163723,
//        volume_24h: 13728947008.2722
//    }
//},
//    slug: "bitcoin",
//    symbol: "BTC",
//    tags: [
//    "mineable"
//    ],
//    total_supply: 17906012
//}