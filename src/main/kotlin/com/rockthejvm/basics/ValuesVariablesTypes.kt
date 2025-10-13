package com.rockthejvm.basics

import java.time.LocalDate

fun main() {
    val name = "Adam"
    val surname: String = "Mudrak"
    var age: Int = 23
    age = 24
    age = 24 + 1
    val birthday: LocalDate = LocalDate.of(2000, 7, 9)
    val isMarried: Boolean = true
    var isUnemployed = true
    isUnemployed = false

    print("$name $surname birthday is on the " +
            "${birthday.dayOfMonth} of ${birthday.month}, ${birthday.year}.\n" +
            "He is ${if (isMarried) "is married" else "is not married"}." +
            " He now ${if (!isUnemployed) "has a job" else "doesn't have a job"}. " +
            "He has a car.")
}