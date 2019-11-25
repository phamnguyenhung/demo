package com.example.myapplication.dto

import com.google.gson.annotations.SerializedName

data class ViewComponentDTO(

    val containers: List<Container>?
)

data class Container(

    val id: Long,

    val name: String,

    @SerializedName("has_note")
    val hasNote: Boolean,

    val groups: List<Group>?
)

data class Group(
    val items: List<Item>
)

data class Item(

    val id: Int,

    val name: String,

    val param: String,

    @SerializedName("is_required")
    val isRequired: Boolean,

    @SerializedName("read_only")
    val readOnly: Boolean? = null,

    val title: String
)
