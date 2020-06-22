package bh.yoo.mvpcoin.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import bh.yoo.mvpcoin.R
import bh.yoo.mvpcoin.contract.MainContract
import bh.yoo.mvpcoin.data.Coin
import bh.yoo.mvpcoin.data.CoinData
import bh.yoo.mvpcoin.injector.ModelInjector
import bh.yoo.mvpcoin.presenter.MainPresenter
import kotlinx.android.synthetic.main.fragment1.*

class Fragment1 : Fragment(), MainContract.View {

    private lateinit var presenter: MainContract.Presenter


    private lateinit var coin: Coin

    companion object {

        val ARG_NUM = "ARG_NUM"

        fun newInstance(num: Int): Fragment1 {
            val fragment1 = Fragment1()
            val bundle = Bundle()
            bundle.putInt(ARG_NUM, num)
            fragment1.arguments = bundle
            return fragment1
        }

        fun newInstance(): Fragment1 {
            return Fragment1()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment1, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        presenter = MainPresenter(this, ModelInjector.provideMainModel())
        coin = Coin(0, ArrayList())

        getdata.setOnClickListener {

            presenter.getData(coin, getNumText())
        }
    }

    fun loadMoreData() {
        presenter.getData(coin.apply { ++page }, getNumText())
    }

    override fun getNumText(): String {
        return num.text.toString()
    }

    override fun onGetData(result: ArrayList<CoinData>) {

        fragmentManager?.findFragmentById(R.id.frame2)?.also {
            (it as Fragment2).also { frag2 ->
                if (result.size > 0) {
                    if (coin.page == 0) {
                        frag2.setData(result)
                    } else {
                        frag2.addData(result)
                    }
                } else {
                    frag2.setIsMax()
                }
            }
        }
    }

    override fun onInputNumError(@StringRes resId: Int) {
        showToast(resId)
    }

    override fun onErrorGetData() {

    }

    override fun enableButton() {
        getdata.isEnabled = true
    }

    override fun disableButton() {
        getdata.isEnabled = false
    }

    private fun showToast(@StringRes resId: Int) {
        Toast.makeText(activity, resId, Toast.LENGTH_SHORT).show()
    }
}