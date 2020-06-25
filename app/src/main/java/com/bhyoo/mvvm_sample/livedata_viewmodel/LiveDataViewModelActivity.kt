package com.bhyoo.mvvm_sample.livedata_viewmodel

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bhyoo.mvvm_sample.R
import kotlinx.android.synthetic.main.activity_view_model.*

class LiveDataViewModelActivity : AppCompatActivity(){


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_model)

        val viewModel = ViewModelProvider(this@LiveDataViewModelActivity).get(LiveDataCounterViewModel::class.java)

        count_text.text = viewModel.count.value.toString()

        plus.setOnClickListener {
            viewModel.increase()
            count_text.text = viewModel.count.value.toString()
        }

        minus.setOnClickListener {
            viewModel.decrease()
            count_text.text = viewModel.count.value.toString()
        }
    }
}