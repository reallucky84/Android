package bh.yoo.mvpcoin

import bh.yoo.mvpcoin.contract.MainContract
import bh.yoo.mvpcoin.contract.MainModel
import bh.yoo.mvpcoin.data.CoinData
import bh.yoo.mvpcoin.injector.BaseUrlInjector
import bh.yoo.mvpcoin.model.MainModelImpl
import bh.yoo.mvpcoin.network.NifMain
import bh.yoo.mvpcoin.network.core.retrofit
import com.google.gson.GsonBuilder
import com.nhaarman.mockitokotlin2.*
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.HttpURLConnection

class ModelUnitTest {

    private lateinit var mainModel: MainModel
    private lateinit var mockWebServer: MockWebServer

    @Before
    fun setUp() {
        mainModel = MainModelImpl()
        mockWebServer = MockWebServer()
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun test_retrofit() {

        val jsonString =
            "{\"8\":{\"circulating_supply\":1.78549857508877E7,\"cmc_rank\":9,\"date_added\":\"2018-11-09T00:00:00.000Z\",\"id\":3602,\"last_updated\":\"2019-08-30T18:51:17.000Z\",\"max_supply\":21000000,\"name\":\"Bitcoin SV\",\"num_market_pairs\":145,\"quote\":{\"USD\":{\"last_updated\":\"2019-08-30T18:51:17.000Z\",\"market_cap\":2.2924252585757027E9,\"percent_change_1h\":-0.170406,\"percent_change_24h\":-1.47767,\"percent_change_7d\":-5.51624,\"price\":128.391323889,\"volume_24h\":3.22208913931333E8}},\"slug\":\"bitcoin-sv\",\"symbol\":\"BSV\",\"tags\":[\"mineable\"],\"total_supply\":1.78549857508877E7},\"4\":{\"circulating_supply\":6.31471240076922E7,\"cmc_rank\":5,\"date_added\":\"2013-04-28T00:00:00.000Z\",\"id\":2,\"last_updated\":\"2019-08-30T18:51:04.000Z\",\"max_supply\":84000000,\"name\":\"Litecoin\",\"num_market_pairs\":538,\"quote\":{\"USD\":{\"last_updated\":\"2019-08-30T18:51:04.000Z\",\"market_cap\":4.0634599537742333E9,\"percent_change_1h\":-0.147577,\"percent_change_24h\":-0.605248,\"percent_change_7d\":-14.3377,\"price\":64.3490898062,\"volume_24h\":2.42287231159193E9}},\"slug\":\"litecoin\",\"symbol\":\"LTC\",\"tags\":[\"mineable\"],\"total_supply\":6.31471240076922E7},\"9\":{\"circulating_supply\":1.96393760925818E10,\"cmc_rank\":10,\"date_added\":\"2014-08-05T00:00:00.000Z\",\"id\":512,\"last_updated\":\"2019-08-30T18:51:03.000Z\",\"name\":\"Stellar\",\"num_market_pairs\":283,\"quote\":{\"USD\":{\"last_updated\":\"2019-08-30T18:51:03.000Z\",\"market_cap\":1.2223193040535805E9,\"percent_change_1h\":-0.600044,\"percent_change_24h\":-0.659007,\"percent_change_7d\":-9.81712,\"price\":0.0622381942426,\"volume_24h\":7.84858019126806E7}},\"slug\":\"stellar\",\"symbol\":\"XLM\",\"total_supply\":1.05283157144904E11},\"5\":{\"circulating_supply\":4.00826941101333E9,\"cmc_rank\":6,\"date_added\":\"2015-02-25T00:00:00.000Z\",\"id\":825,\"last_updated\":\"2019-08-30T18:51:12.000Z\",\"name\":\"Tether\",\"num_market_pairs\":3016,\"platform\":{\"id\":83,\"name\":\"Omni\",\"slug\":\"omni\",\"symbol\":\"OMNI\",\"token_address\":\"31\"},\"quote\":{\"USD\":{\"last_updated\":\"2019-08-30T18:51:12.000Z\",\"market_cap\":4.02062509595401E9,\"percent_change_1h\":0.0135326,\"percent_change_24h\":0.166516,\"percent_change_7d\":-0.0104562,\"price\":1.00308254852,\"volume_24h\":1.59553807843482E10}},\"slug\":\"tether\",\"symbol\":\"USDT\",\"total_supply\":4.09505749336343E9},\"6\":{\"circulating_supply\":9.296512618605E8,\"cmc_rank\":7,\"date_added\":\"2017-07-01T00:00:00.000Z\",\"id\":1765,\"last_updated\":\"2019-08-30T18:51:06.000Z\",\"name\":\"EOS\",\"num_market_pairs\":338,\"quote\":{\"USD\":{\"last_updated\":\"2019-08-30T18:51:06.000Z\",\"market_cap\":2.989122295088888E9,\"percent_change_1h\":-0.124671,\"percent_change_24h\":-0.57441,\"percent_change_7d\":-12.8349,\"price\":3.2153156971,\"volume_24h\":1.2186638896758E9}},\"slug\":\"eos\",\"symbol\":\"EOS\",\"total_supply\":1.0263512731073E9},\"1\":{\"circulating_supply\":1.07537936374E8,\"cmc_rank\":2,\"date_added\":\"2015-08-07T00:00:00.000Z\",\"id\":1027,\"last_updated\":\"2019-08-30T18:51:21.000Z\",\"name\":\"Ethereum\",\"num_market_pairs\":5629,\"quote\":{\"USD\":{\"last_updated\":\"2019-08-30T18:51:21.000Z\",\"market_cap\":1.8140427540533985E10,\"percent_change_1h\":-0.0330049,\"percent_change_24h\":-0.510765,\"percent_change_7d\":-13.1883,\"price\":168.688633539,\"volume_24h\":5.77432384644399E9}},\"slug\":\"ethereum\",\"symbol\":\"ETH\",\"tags\":[\"mineable\"],\"total_supply\":1.07537936374E8},\"0\":{\"circulating_supply\":17906012,\"cmc_rank\":1,\"date_added\":\"2013-04-28T00:00:00.000Z\",\"id\":1,\"last_updated\":\"2019-08-30T18:51:28.000Z\",\"max_supply\":21000000,\"name\":\"Bitcoin\",\"num_market_pairs\":7919,\"quote\":{\"USD\":{\"last_updated\":\"2019-08-30T18:51:28.000Z\",\"market_cap\":1.7115554031886005E11,\"percent_change_1h\":-0.127291,\"percent_change_24h\":0.328918,\"percent_change_7d\":-8.00576,\"price\":9558.55163723,\"volume_24h\":1.37289470082722E10}},\"slug\":\"bitcoin\",\"symbol\":\"BTC\",\"tags\":[\"mineable\"],\"total_supply\":17906012},\"2\":{\"circulating_supply\":42932866967,\"cmc_rank\":3,\"date_added\":\"2013-08-04T00:00:00.000Z\",\"id\":52,\"last_updated\":\"2019-08-30T18:51:03.000Z\",\"max_supply\":100000000000,\"name\":\"XRP\",\"num_market_pairs\":449,\"quote\":{\"USD\":{\"last_updated\":\"2019-08-30T18:51:03.000Z\",\"market_cap\":1.0924204422702969E10,\"percent_change_1h\":-0.187121,\"percent_change_24h\":-1.85857,\"percent_change_7d\":-7.81634,\"price\":0.254448519152,\"volume_24h\":9.26785215623047E8}},\"slug\":\"ripple\",\"symbol\":\"XRP\",\"total_supply\":99991366793},\"7\":{\"circulating_supply\":1.29175490242999E8,\"cmc_rank\":8,\"date_added\":\"2017-07-25T00:00:00.000Z\",\"id\":1839,\"last_updated\":\"2019-08-30T18:51:06.000Z\",\"max_supply\":187536713,\"name\":\"Binance Coin\",\"num_market_pairs\":249,\"quote\":{\"USD\":{\"last_updated\":\"2019-08-30T18:51:06.000Z\",\"market_cap\":2.8788970804443746E9,\"percent_change_1h\":-0.384526,\"percent_change_24h\":1.18229,\"percent_change_7d\":-18.1815,\"price\":22.2867130214,\"volume_24h\":1.79496506914164E8}},\"slug\":\"binance-coin\",\"symbol\":\"BNB\",\"total_supply\":1.89175490242499E8},\"3\":{\"circulating_supply\":17975975,\"cmc_rank\":4,\"date_added\":\"2017-07-23T00:00:00.000Z\",\"id\":1831,\"last_updated\":\"2019-08-30T18:51:08.000Z\",\"max_supply\":21000000,\"name\":\"Bitcoin Cash\",\"num_market_pairs\":378,\"quote\":{\"USD\":{\"last_updated\":\"2019-08-30T18:51:08.000Z\",\"market_cap\":5.05177032824821E9,\"percent_change_1h\":0.241432,\"percent_change_24h\":0.434728,\"percent_change_7d\":-10.2967,\"price\":281.02900278,\"volume_24h\":1.40694915588653E9}},\"slug\":\"bitcoin-cash\",\"symbol\":\"BCH\",\"tags\":[\"mineable\"],\"total_supply\":17975975}}"
        val baseUrlInjector: BaseUrlInjector = mock()
        whenever(baseUrlInjector.getBaseUrl()).thenReturn(mockWebServer.url("/").toString())
        val mockResponse = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_OK)
            .setBody(jsonString)
        mockWebServer.enqueue(mockResponse)
        val apiService = retrofit(NifMain.MainApi::class.java, baseUrlInjector);
        val call = apiService.getData(1, 5);
        val response: Response<ArrayList<CoinData>> = call.execute()
        assert(response.isSuccessful)
        assertNotNull(response.body())
        assertEquals(10, response.body()?.size)
    }

    @Test
    fun test_model() {

        val startAt = 1
        val endAt = 5

        val coinDatas = ArrayList<CoinData>()
        val json = "{\"circulating_supply\":17906012,\"cmc_rank\":1,\"date_added\":\"2013-04-28T00:00:00.000Z\",\"id\":1,\"last_updated\":\"2019-08-30T18:51:28.000Z\",\"max_supply\":21000000,\"name\":\"Bitcoin\",\"num_market_pairs\":7919,\"quote\":{\"USD\":{\"last_updated\":\"2019-08-30T18:51:28.000Z\",\"market_cap\":1.7115554031886005E11,\"percent_change_1h\":-0.127291,\"percent_change_24h\":0.328918,\"percent_change_7d\":-8.00576,\"price\":9558.55163723,\"volume_24h\":1.37289470082722E10}},\"slug\":\"bitcoin\",\"symbol\":\"BTC\",\"tags\":[\"mineable\"],\"total_supply\":17906012}"
        val coinData = GsonBuilder().create().fromJson(json, CoinData::class.java)
        coinDatas.add(coinData)

        val mockedNifMainApi: NifMain.MainApi = mock()

        val mockedCall: Call<ArrayList<CoinData>> = mock()

        whenever(mockedNifMainApi.getData(startAt, endAt)).thenReturn(mockedCall)

        doAnswer {
            val mockCallback = it.getArgument<Callback<ArrayList<CoinData>>>(0)
            val response = Response.success(coinDatas)
            mockCallback.onResponse(mockedCall, response)
        }.`when`(mockedCall).enqueue(any<Callback<ArrayList<CoinData>>>())

//        val callback = argumentCaptor<MainContract.GetDataCallback>()
        val callback: MainContract.GetDataCallback = mock()
        mainModel.getData(1, 5, mockedNifMainApi, callback)

        verify(callback).onGetData(coinDatas)
    }

}
