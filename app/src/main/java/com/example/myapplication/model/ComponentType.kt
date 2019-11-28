package com.example.myapplication.model

import com.example.myapplication.observable.SelfObservable
import com.example.myapplication.observable.Validator
import com.example.myapplication.observable.ValidatorOwner
import com.example.myapplication.validator.PhoneValidator
import com.example.myapplication.validator.PlainValidator

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
        const val PHONE_TYPE = 0
        const val NOTE_TYPE = 1
        const val PLAIN_TYPE = 2
        const val SESSION_NAME_TYPE = 3
        const val CHECKOUT_BUTTON_TYPE = 4
    }

    val id: Int get() = 0
    val type: ComponentType get() = ComponentType.UN_KNOW
    val isRequired: Boolean get() = false
}

abstract class SubmittableComponent<T>(
    var param: String = ""
) : SelfObservable<T>(), ViewComponent {

    override var id: Int = super.id
    override var type: ComponentType = super.type
    override var isRequired: Boolean = super.isRequired
}

class PhoneComponent(
    var name: String,
    val title: String = ""
) : SubmittableComponent<PhoneComponent>(), ValidatorOwner {

    override var validator: Validator<String> = PhoneValidator()

    override val isValid: Boolean
        get() = validator.accept(name)
}

class NoteComponent(val name: String = "") : SubmittableComponent<NoteComponent>()

class PlainEdtComponent(
    var name: String = ""
) : SubmittableComponent<PlainEdtComponent>(), ValidatorOwner {

    override var validator: Validator<String> = PlainValidator()

    override val isValid: Boolean
        get() = validator.accept(name)
}

class SessionNameComponent : ViewComponent

class CheckoutButtonComponent : ViewComponent