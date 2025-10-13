package com.rockthejvm.oop

class Person(val firstName: String, val lastName: String, var age: Int) {
    val fullName = "$firstName $lastName"

    lateinit var favouriteMovie: String

    fun initializeFavouriteMovie(value: String) {
        favouriteMovie = value
    }

    init {
        println("Initializing a Person with the name $firstName and the surname $lastName.")
    }

    init {
        println("Yet another init after the first one. ")
    }

    fun greet() =
        "Hi everyone, my name is $firstName"

    fun greet(firstName: String): String =
        "Hi $firstName, my name is ${this.firstName}!"

    override fun toString(): String {
        return "Person(firstName='$firstName', lastName='$lastName', age=$age, fullName='$fullName', favouriteMovie='$favouriteMovie')"
    }

    constructor(firstName: String, lastName: String): this(firstName, lastName, 18)
    constructor(): this("Adam", "Mudrak")
}

fun main() {
    println("\nPRIMARY CONSTRUCTOR")
    val adam = Person("Adam", "Mudrak", 25)
    println(adam.fullName)
    println(adam.age)
    adam.age = 26
    println(adam.age)
    println(adam.firstName)
    println(adam.lastName)
    println(adam.greet())
    println(adam.greet("Or"))

    println("\nSECONDARY CONSTRUCTOR")
    val anotherAdam = Person("Adam", "Mudrak")
    println("${anotherAdam.firstName} ${anotherAdam.lastName} ${anotherAdam.age}")

    println("\nEMPTY CONSTRUCTOR")
    val emptyAdam = Person()
    println("${emptyAdam.firstName} ${emptyAdam.lastName} ${emptyAdam.age}")

    println("Getters and setters")
    adam.favouriteMovie = ("Inception")
    println(adam.favouriteMovie)
    adam.initializeFavouriteMovie("Another movie")
    println(adam)
}