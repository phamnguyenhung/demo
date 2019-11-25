package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.model.PhoneComponent
import com.example.myapplication.model.PlainEdtComponent
import com.example.myapplication.model.SessionNameComponent
import com.example.myapplication.model.ViewComponent
import com.example.myapplication.viewholder.FormAdapter
import com.example.myapplication.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private lateinit var formAdapter: FormAdapter
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java).apply {
            init()
        }

        formAdapter = FormAdapter(/*createData()*/)
        rvForm.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rvForm.adapter = formAdapter
    }

    private fun MainViewModel.init() {
        call(loadJsonFromAsset())
        viewComponents.observe(this@MainActivity, Observer {
            formAdapter.setData(it)
        })
    }

    private fun loadJsonFromAsset() =
        applicationContext
            .assets
            .open("address-delivery-container-sg.json")
            .bufferedReader()
            .use {
                it.readText()
            }


    private fun createData(): List<ViewComponent> {
        return listOf(
            SessionNameComponent(),
            PhoneComponent("name", "title"),
            PlainEdtComponent("plain name")
        )
    }
}
