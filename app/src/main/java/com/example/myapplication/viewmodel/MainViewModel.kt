package com.example.myapplication.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.dto.ViewComponentDTO
import com.example.myapplication.dto.ViewComponentDTOMapper
import com.example.myapplication.model.CheckoutButtonComponent
import com.example.myapplication.model.ViewComponent
import com.google.gson.Gson
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainViewModel(context: Context) : ViewModel() {

    private val mapper = ViewComponentDTOMapper()
    private val gson = Gson()

    val viewComponents = MutableLiveData<List<ViewComponent>>()

    init {
        call(context.assets
            .open("address-delivery-container-sg.json")
            .bufferedReader()
            .use { it.readText() })
    }

    fun call(json: String) {
        val a = Single.fromCallable {
            gson.fromJson(json, ViewComponentDTO::class.java)
        }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                viewComponents.postValue(mapper(it) + CheckoutButtonComponent())
            }, {

            })
    }
}