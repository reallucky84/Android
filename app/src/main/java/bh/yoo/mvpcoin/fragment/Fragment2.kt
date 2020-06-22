package bh.yoo.mvpcoin.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import bh.yoo.mvpcoin.R
import bh.yoo.mvpcoin.adapter.CoinDataAdapter
import bh.yoo.mvpcoin.data.CoinData
import kotlinx.android.synthetic.main.fragment2.view.*

class Fragment2: Fragment(), CoinDataAdapter.EndlessScrollListener {


    private lateinit var coinDataAdapter: CoinDataAdapter

    companion object {
        fun newInstance(): Fragment2 {
            return Fragment2()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment2, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        coinDataAdapter = CoinDataAdapter(this)

        view.recycler.adapter = coinDataAdapter
        view.recycler.layoutManager = LinearLayoutManager(context)
    }

    fun setData(data: ArrayList<CoinData>){
        Log.i("ddd", "data=${data}")
        coinDataAdapter.setData(data)
    }

    fun addData(data: ArrayList<CoinData>){
        coinDataAdapter.addData(data)
    }

    fun setIsMax(){
        coinDataAdapter.setIsMax()
    }

    override fun loadData() {

        fragmentManager?.findFragmentById(R.id.frame1)?.also {
            (it as Fragment1).also { frag1 ->
                frag1.loadMoreData()
            }
        }

    }
}