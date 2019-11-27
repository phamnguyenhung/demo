package com.example.myapplication

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
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
        viewModel = MainViewModel(applicationContext)

        formAdapter = FormAdapter(rvForm)

        viewModel.viewComponents.observe(this, Observer {
            formAdapter.setData(it)
        })
    }

    private fun createData(): List<ViewComponent> {
        return listOf(
            SessionNameComponent(),
            PhoneComponent("name", "title"),
            PlainEdtComponent("plain name")
        )
    }
}
