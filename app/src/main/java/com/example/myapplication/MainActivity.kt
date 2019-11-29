package com.example.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.myapplication.viewholder.FormAdapter
import com.example.myapplication.viewmodel.MainViewModel
import com.example.myapplication.viewmodel.ResourceLoader
import kotlinx.android.synthetic.main.activity_main.*
import java.io.InputStream


class MainActivity : AppCompatActivity() {

    private lateinit var formAdapter: FormAdapter
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = MainViewModel(object : ResourceLoader {
            override fun streamOf(s: String): InputStream {
                return applicationContext.assets.open(s)
            }
        })
        formAdapter = FormAdapter(rvForm)

        viewModel.viewComponents.observe(this, Observer {
            formAdapter.setData(it)
        })
    }
}
