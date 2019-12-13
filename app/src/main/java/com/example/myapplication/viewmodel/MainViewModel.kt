package com.example.myapplication.viewmodel

import androidx.arch.core.executor.ArchTaskExecutor
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.myapplication.dto.ViewComponentDTO
import com.example.myapplication.dto.ViewComponentsOf
import com.example.myapplication.model.SubmittableComponent
import com.example.myapplication.model.ViewComponent
import com.example.myapplication.observable.Observable
import com.example.myapplication.observable.ValidateAble
import com.example.myapplication.observable.allOf
import com.google.gson.Gson
import java.io.InputStream

interface ResourceLoader {
    fun open(s: String): InputStream
}

class MainViewModel(loader: ResourceLoader) : ViewModel() {

    val requestPayload = MutableLiveData<Map<String, String>>()
    private val gson = Gson()

    val viewComponents = MutableLiveData<List<ViewComponent>>()

    val isReadyToMakeOrder = viewComponents.switchMap {
        allOf(it.filterIsInstance<Observable<out Any>>())
    }.map { observables ->
        observables
                .filterIsInstance(ValidateAble::class.java)
                .all {
                    it.isValid
                }
    }

    init {
        ArchTaskExecutor.getIOThreadExecutor().execute {
            val data = gson.fromJson(
                    loader.open("address-delivery-container-sg.json")
                            .bufferedReader()
                            .use {
                                it.readText()
                            },
                    ViewComponentDTO::class.java
            )
            viewComponents.postValue(ViewComponentsOf(data))
        }
    }

    fun submit() {
        val payload = hashMapOf<String, String>()

        ArchTaskExecutor.getIOThreadExecutor().execute {
            viewComponents.value?.forEach {
                if (it is SubmittableComponent<*>) {
                    payload[it.param] = it.name
                }
            }

            requestPayload.postValue(payload)
        }
    }

}

fun <T, V> LiveData<T>.switchMap(function: (T) -> LiveData<V>): LiveData<V> {
    return Transformations.switchMap(this, function)
}

fun <T, V> LiveData<T>.map(function: (T) -> V): LiveData<V> {
    return Transformations.map(this, function)
}
