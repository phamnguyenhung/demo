package com.example.myapplication

import com.example.myapplication.viewmodel.MainViewModel
import com.example.myapplication.viewmodel.ResourceLoader
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock


class InitViewModelTest {

    private lateinit var viewModel: MainViewModel

    private val loader = mock(ResourceLoader::class.java)

    @get:Rule
    val testRule = LiveDataRule()

    @Test
    fun testLoadDataFromJsonConfig() {
        val file = "address-delivery-container-sg.json"

        Mockito.`when`(loader.streamOf(file))
            .thenReturn(javaClass.classLoader!!.getResource(file).openStream())

        viewModel = MainViewModel(loader)

        val value = viewModel.viewComponents.awaitValue
        assert(value != null && value.isNotEmpty())
        assert(value!!.size == 6) { "Just 6 component but received ${value.size}" }
    }
}