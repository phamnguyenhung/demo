package com.example.myapplication.viewmodel

import androidx.arch.core.executor.ArchTaskExecutor
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.dto.ViewComponentDTO
import com.example.myapplication.dto.ViewComponentDTOMapper
import com.example.myapplication.model.CheckoutButtonComponent
import com.example.myapplication.model.ViewComponent
import com.google.gson.Gson
import java.io.InputStream

interface ResourceLoader {
    fun streamOf(s: String): InputStream
}

class MainViewModel(loader: ResourceLoader) : ViewModel() {

    private val mapper = ViewComponentDTOMapper()
    private val gson = Gson()

    val viewComponents = MutableLiveData<List<ViewComponent>>()

    init {
        ArchTaskExecutor.getIOThreadExecutor().execute {
            val data = gson.fromJson(
                loader.streamOf("address-delivery-container-sg.json")
                    .bufferedReader()
                    .use { it.readText() }, ViewComponentDTO::class.java
            )
            viewComponents.postValue(mapper(data) + CheckoutButtonComponent())
        }
    }

}