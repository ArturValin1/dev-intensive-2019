package ru.skillbranch.devintensive.models

import ru.skillbranch.devintensive.extensions.humanizeDiff
import java.util.*

class ImageMessage(
    id: String,
    from: User?,
    chat: Chat,
    isInComing: Boolean = false,
    date: Date = Date(),
    var image: String?
) : BaseMessage(id, from, chat, isInComing, date) {
    override fun formatMessage(): String =
        "$id ${from?.firstName} ${if (isInComing) "получил" else "отправил"} изображение $image ${date.humanizeDiff()}"
}