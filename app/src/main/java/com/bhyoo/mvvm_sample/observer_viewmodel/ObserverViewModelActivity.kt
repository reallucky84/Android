package com.bhyoo.mvvm_sample.observer_viewmodel

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bhyoo.mvvm_sample.R
import kotlinx.android.synthetic.main.activity_view_model.*

class ObserverViewModelActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_model)

        val viewModel =
            ViewModelProvider(this@ObserverViewModelActivity).get(ObserverCounterViewModel::class.java)

        plus.setOnClickListener {
            viewModel.increase()
        }

        minus.setOnClickListener {
            viewModel.decrease()
        }

        viewModel.count.observe(this@ObserverViewModelActivity, Observer<Int> {
            count_text.text = it.toString()
        })

    }
}