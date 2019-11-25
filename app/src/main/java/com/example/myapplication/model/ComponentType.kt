package com.example.myapplication.model

import com.example.myapplication.model.ViewComponent.Companion.CHECKOUT_BUTTON_TYPE
import com.example.myapplication.model.ViewComponent.Companion.SESSION_NAME_TYPE

val SESSION_NAME_IDS = listOf(200, 201)
val PHONE_NUMBER_EDT_IDS = listOf(199)
val PLAIN_EDT_IDS = listOf(0, 1, 2, 3, 4, 5, 6, 7)
val NOTE_EDT_IDS = listOf(99)


enum class ComponentType(val ids: List<Int> = emptyList()) {
    UN_KNOW,
    SESSION_NAME(SESSION_NAME_IDS),
    PHONE_NUMBER_EDT(PHONE_NUMBER_EDT_IDS),
    PLAIN_EDT(PLAIN_EDT_IDS),
    NOTE_EDT(NOTE_EDT_IDS);

    companion object {
        fun getTypeById(id: Int): ComponentType = when {
            SESSION_NAME_IDS.contains(id) -> SESSION_NAME
            PHONE_NUMBER_EDT_IDS.contains(id) -> PHONE_NUMBER_EDT
            PLAIN_EDT_IDS.contains(id) -> PLAIN_EDT
            NOTE_EDT_IDS.contains(id) -> NOTE_EDT
            else -> UN_KNOW
        }
    }
}

interface ViewComponent {
    companion object {
        val PHONE_TYPE = 0
        val NOTE_TYPE = 1
        val PLAIN_TYPE = 2
        val SESSION_NAME_TYPE = 3
        val CHECKOUT_BUTTON_TYPE = 4
    }

    val viewType: Int
}

abstract class BaseComponent(
    var id: Int = 0,
    var type: ComponentType = ComponentType.UN_KNOW,
    var isRequired: Boolean = false
) : ViewComponent

abstract class SubmittedComponent(
    var param: String = ""
) : BaseComponent()

class PhoneComponent(
    val name: String = "",
    val title: String = ""
) : SubmittedComponent() {

    override val viewType: Int
        get() = ViewComponent.PHONE_TYPE
}

class NoteComponent : SubmittedComponent() {

    override val viewType: Int
        get() = ViewComponent.NOTE_TYPE
}

class PlainEdtComponent(
    val name: String = ""
) : SubmittedComponent() {

    override val viewType: Int
        get() = ViewComponent.PLAIN_TYPE
}

class SessionNameComponent : BaseComponent() {

    override val viewType: Int
        get() = SESSION_NAME_TYPE
}

class CheckoutButtonComponent : ViewComponent {

    override val viewType: Int
        get() = CHECKOUT_BUTTON_TYPE
}