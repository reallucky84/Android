package bh.yoo.mvpcoin.utils

import bh.yoo.mvpcoin.data.CoinData
import com.google.gson.*
import java.lang.reflect.Type
import kotlin.reflect.KClass

class CoinDeserializer<T : Any>(private val c: KClass<T>) : JsonDeserializer<ArrayList<T>> {

    override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): ArrayList<T> {

        return jsonToArrayList(json.asJsonObject, c)
    }

    fun <T : Any> jsonToArrayList(jsonObject: JsonObject, c: KClass<T>): ArrayList<T> {

        val arrayList = ArrayList<T>()
        val tempObject = jsonObject.asJsonObject

        val keys = tempObject.keySet()
        for (key in keys) {
            val coinData = Gson().fromJson(tempObject.getAsJsonObject(key), c.java)
            arrayList.add(coinData)
        }
        return arrayList
    }
}