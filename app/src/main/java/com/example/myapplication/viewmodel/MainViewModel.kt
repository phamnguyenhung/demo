package com.example.myapplication.viewmodel

import androidx.lifecycle.LiveData
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

class MainViewModel : ViewModel() {

    private val mapper = ViewComponentDTOMapper()
    private val gson = Gson()

    private val _viewComponents = MutableLiveData<List<ViewComponent>>()
    val viewComponents: LiveData<List<ViewComponent>>
        get() = _viewComponents

    fun call(json: String) {
        val a = Single.fromCallable {
            gson.fromJson(json, ViewComponentDTO::class.java)
        }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _viewComponents.postValue(mapper(it) + CheckoutButtonComponent())
            }, {

            })
    }
}