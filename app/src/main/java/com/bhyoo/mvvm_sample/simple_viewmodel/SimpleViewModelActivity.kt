package com.bhyoo.mvvm_sample.simple_viewmodel

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bhyoo.mvvm_sample.R
import kotlinx.android.synthetic.main.activity_view_model.*

class SimpleViewModelActivity : AppCompatActivity(){


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_model)

        val viewModel = ViewModelProvider(this@SimpleViewModelActivity).get(SimpleCounterViewModel::class.java)

        count_text.text = viewModel.count.toString()

        plus.setOnClickListener {
            viewModel.count++
            count_text.text = viewModel.count.toString()
        }

        minus.setOnClickListener {
            viewModel.count--
            count_text.text = viewModel.count.toString()
        }
    }
}