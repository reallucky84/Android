package bh.yoo.mvpcoin.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import bh.yoo.mvpcoin.R
import bh.yoo.mvpcoin.adapter.CoinDataAdapter
import bh.yoo.mvpcoin.fragment.Fragment1
import bh.yoo.mvpcoin.fragment.Fragment2

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.frame1, Fragment1.newInstance())
        fragmentTransaction.add(R.id.frame2, Fragment2.newInstance())
        fragmentTransaction.commit()
    }



}
