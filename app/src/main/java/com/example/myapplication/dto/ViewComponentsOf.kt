package com.example.myapplication.dto

import com.example.myapplication.model.*

object ViewComponentsOf {

    operator fun invoke(viewComponentDto: ViewComponentDTO): List<ViewComponent> {
        val result = mutableListOf<ViewComponent>()
        viewComponentDto.containers?.firstOrNull()?.let {
            it.groups?.forEach { group ->
                result.onTraversal(group)
            }

            if (it.hasNote) {
                result += NoteComponent()
            }
        }
        return result
    }

    private fun MutableList<ViewComponent>.onTraversal(group: Group) {
        group.items.forEach { item ->
            onTraversal(item)
        }
    }

    private fun MutableList<ViewComponent>.onTraversal(item: Item) =
        when (ComponentType.getTypeById(item.id)) {
            ComponentType.SESSION_NAME -> {
            }
            ComponentType.PHONE_NUMBER_EDT -> this += toPhoneComponent(item)
            ComponentType.PLAIN_EDT -> this += toPlain(item)
            ComponentType.NOTE_EDT -> this += NoteComponent()
            else -> {
            }
        }

    private fun toPlain(item: Item) = PlainEdtComponent(
        hint = item.name
    ).apply {
        isRequired = item.isRequired
        param = item.param
        id = item.id
        type = ComponentType.PLAIN_EDT
    }

    private fun toPhoneComponent(item: Item) = PhoneComponent(
        hint = item.name,
        title = item.title
    ).apply {
        isRequired = item.isRequired
        param = item.param
        id = item.id
        type = ComponentType.PHONE_NUMBER_EDT
    }
}