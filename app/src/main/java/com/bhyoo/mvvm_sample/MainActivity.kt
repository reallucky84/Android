package com.bhyoo.mvvm_sample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bhyoo.mvvm_sample.livedata_viewmodel.LiveDataViewModelActivity
import com.bhyoo.mvvm_sample.observer_viewmodel.ObserverViewModelActivity
import com.bhyoo.mvvm_sample.simple_viewmodel.SimpleViewModelActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        simple_viewmodel.setOnClickListener {
            startActivity(Intent(this@MainActivity, SimpleViewModelActivity::class.java))
        }

        livedata_viewmodel.setOnClickListener {
            startActivity(Intent(this@MainActivity, LiveDataViewModelActivity::class.java))
        }

        observer_viewmodel.setOnClickListener {
            startActivity(Intent(this@MainActivity, ObserverViewModelActivity::class.java))
        }


    }
}