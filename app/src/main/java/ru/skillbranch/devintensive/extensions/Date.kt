package ru.skillbranch.devintensive.extensions

import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

const val SECOND = 1000L
const val MUNUTE = 60 * SECOND
const val HOUR = 60 * MUNUTE
const val DAY = 24 * HOUR

fun Date.format(pattern: String = "HH:mm:ss dd.MM.YY"): String {
    val dateFormat = SimpleDateFormat(pattern, Locale("ru"))
    return dateFormat.format(this)
}

fun Date.add(value: Int, timeUnits: MyTimeUnits): Date {
    var time = this.time

    time += when (timeUnits) {
        MyTimeUnits.SECOND -> value * SECOND
        MyTimeUnits.MINUTE -> value * MUNUTE
        MyTimeUnits.HOUR -> value * HOUR
        MyTimeUnits.DAY -> value * DAY
    }
    this.time = time
    return this
}

fun Date.humanizeDiff(date: Date = Date()): String {
    var timeOne = this.time
    val diffOne: Long = Date().time - timeOne
    val diffSecond: Long = TimeUnit.MILLISECONDS.toSeconds(diffOne)
    var result = when (diffSecond) {
        in (0..1) -> "только что"
        in (2..75) -> "минуту назад"
        in (76..5 * 60 - 1) -> "${diffSecond / 60} минуты назад"
        in (5 * 60..45 * 60) -> "${diffSecond / 60} минут назад"
        in (45 * 60 + 1..75 * 60) -> "час назад"
        in (75 * 60 + 1..5 * 3600 - 1) -> "${diffSecond / 3600} часа назад"
        in (5 * 3600..22 * 3600) -> "${diffSecond / 3600} часов назад"
        in (22 * 3600 + 1..26 * 3600) -> "день назад"
        in (26 * 3600 + 1..5 * 86400 - 1) -> "${diffSecond / 86400} дня назад"
        in (26 * 3600 + 1..360 * 86400) -> "${diffSecond / 86400} дней назад"
        in (-1..0) -> "только что"
        in (-75..-2) -> "через минуту"
        in (-5 * 60 + 1..-76) -> "через ${Math.abs(diffSecond / 60) + 1} минуты"
        in (-45 * 60..-5 * 60) -> "через ${Math.abs(diffSecond / 60) + 1} минут"
        in (-75 * 60 + 1..-45 * 60 - 1) -> "через час"
        in (-5 * 3600 + 1..-75 * 60) -> "через ${Math.abs(diffSecond / 3600) + 1} часа"
        in (-22 * 3600..-5 * 3600) -> "через ${Math.abs(diffSecond / 3600) + 1} часов"
        in (-26 * 3600..-22 * 3600 - 1) -> "через день"
        in (-5 * 86400 + 1..-26 * 3600 - 1) -> "через ${Math.abs(diffSecond / 86400) + 1} дня"
        in (-360 * 86400..-5 * 86400) -> "через ${Math.abs(diffSecond / 86400) + 1} дней"
        else -> if (diffSecond < -360 * 86400 - 1) "более чем через год" else if (diffSecond > 360 * 86400) "более года назад"
        else "неизвестно"
    }
    return result
}

enum class MyTimeUnits {
    SECOND,
    MINUTE,
    HOUR,
    DAY
}