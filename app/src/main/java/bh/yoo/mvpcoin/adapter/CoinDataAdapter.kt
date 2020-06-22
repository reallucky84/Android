package bh.yoo.mvpcoin.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import bh.yoo.mvpcoin.R
import bh.yoo.mvpcoin.data.CoinData
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_coindata.view.*

class CoinDataAdapter(private val endlessScrollListener: EndlessScrollListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val items = ArrayList<CoinData>()
    private var VISIBLE_THRESHOLD = 1
    private var isMax = false


    fun setData(newData: ArrayList<CoinData>) {
        items.clear()
        items.addAll(newData)
        notifyDataSetChanged()
    }

    fun addData(newData: ArrayList<CoinData>) {
        items.addAll(newData)
        notifyDataSetChanged()
    }

    fun setIsMax(){
        isMax = true
        VISIBLE_THRESHOLD = Int.MAX_VALUE
    }


    inner class CoinDataVH(parent: ViewGroup) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_coindata, parent, false)
    ) {

        val thumbIV = itemView.thumb
        val symbolTV = itemView.symbol
        val circulating_supply = itemView.circulating_supply

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return CoinDataVH(parent)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        items[position].let { data ->
            with(holder as CoinDataVH) {
                Glide.with(itemView.context).load("https://s2.coinmarketcap.com/static/img/coins/64x64/${data.id}.png").into(thumbIV)

                symbolTV.text = "${data.name}(${data.symbol})"
                circulating_supply.text = data.circulating_supply.toBigDecimal().toLong().toString()
                if (position == itemCount - VISIBLE_THRESHOLD) {
                    endlessScrollListener.loadData()
                }
            }
        }

    }

    interface EndlessScrollListener {
        fun loadData()
    }


}