package com.example.myapplication.viewmodel

import android.content.Context
import androidx.arch.core.executor.ArchTaskExecutor
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.dto.ViewComponentDTO
import com.example.myapplication.dto.ViewComponentDTOMapper
import com.example.myapplication.model.CheckoutButtonComponent
import com.example.myapplication.model.ViewComponent
import com.google.gson.Gson

class MainViewModel(context: Context) : ViewModel() {

    private val mapper = ViewComponentDTOMapper()
    private val gson = Gson()

    val viewComponents = MutableLiveData<List<ViewComponent>>()

    init {
        ArchTaskExecutor.getIOThreadExecutor().execute {
            val data = gson.fromJson(
                context.assets
                    .open("address-delivery-container-sg.json")
                    .bufferedReader()
                    .use { it.readText() }, ViewComponentDTO::class.java
            )
            viewComponents.postValue(mapper(data) + CheckoutButtonComponent())
        }
    }

}