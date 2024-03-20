package ru.netology

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

fun main() {
    val secondDifference = compareSeconds("19.03.2024 10:10:20")
    agoToText(secondDifference)
}

fun agoToText(secondDifference: Long) {
    when (secondDifference) {
        in 0..60 -> println("был(а) только что")
        in 61..(60 * 60) -> println("был(а) ${secondDifference / 60} ${textMinutesToRightForm(secondDifference)}")
        in (60 * 60 + 1)..(24 * 60 * 60) -> println(
            "был(а) ${secondDifference / (60 * 60)} ${
                textHoursToRightForm(
                    secondDifference
                )
            }"
        )

        in (24 * 60 * 60 + 1)..(2 * 24 * 60 * 60) -> println("был(а) вчера")
        in (2 * 24 * 60 * 60 + 1)..(3 * 24 * 60 * 60) -> println("был(а) позавчера")
        else -> "был(а) давно"
    }
}

fun compareSeconds(lastTimeComingIn: String): Long {
    // задаем паттерн для времени и даты
    val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss")
    // парсим строку в объект LocalDateTime
    val lastTime = LocalDateTime.parse(lastTimeComingIn, formatter)
    // получаем время сейчас
    val current = LocalDateTime.now()
    // возвращаем разницу во времени в секундах
    return ChronoUnit.SECONDS.between(lastTime, current)
}

fun textMinutesToRightForm(secondDifference: Long): String {
    var rightText = ""
    when (secondDifference / 60) {
        1L, 21L, 31L, 41L, 51L -> rightText = "минуту назад"
        2L, 22L, 32L, 42L, 52L -> rightText = "минуты назад"
        5L, 25L, 35L, 45L, 55L -> rightText = "минут назад"
        11L, 12L, 13L, 14L -> rightText = "минут назад"
    }
    return rightText
}

fun textHoursToRightForm(secondDifference: Long): String {
    var rightText = ""
    when (secondDifference / (60 * 60)) {
        1L, 21L -> rightText = "час назад"
        2L, 3L, 4L, 22L, 23L -> rightText = "часа назад"
        in 5L..20L -> rightText = "часов назад"
    }
    return rightText
}

