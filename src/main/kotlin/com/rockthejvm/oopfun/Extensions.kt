package com.rockthejvm.oopfun

object Extensions {

    fun Int.multiply(string: String): String {
        val builder: StringBuilder = StringBuilder()
        for (i in 1..this) {
            builder.append(string)
        }
        return builder.toString()
    }

    val Int.nDigits: Int
        get() {
            return this.toString().length
        }

    class Person(val name: String, private val age: Int = 18)

    fun Person.greet() = "Hello! My name is $name. My is \$age (private - so not accessible)"


    @JvmStatic
    fun main(args: Array<String>) {
        println(5.multiply("Adam "))
        println(12345.nDigits)
        println(Person("Adam", 18).greet())
    }
}