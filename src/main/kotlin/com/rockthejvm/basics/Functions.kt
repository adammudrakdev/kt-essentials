package com.rockthejvm.basics

fun interpolationPrinter(arg: String): Unit =
    println("I print arg: $arg")

fun noArgFunction(): Unit =
    println("No arg fun")

fun concatenator(string: String = "Default;", count:Int = 10): Unit =
    println("$string ".repeat(count))


fun complexFunction(string: String) = println(simpleStringRepeater(string))

fun simpleStringRepeater(string: String, count: Int = 10) = string.repeat(count)


fun anotherComplexFunction(int: Int = 5): Int {
    fun multiplier(multiplier: Int = 10): Int {
        return int * multiplier
    }
    return multiplier()
}
fun main() {
    interpolationPrinter("Interpolation test")
    noArgFunction()
    concatenator("Concatenator test;", 3)
    concatenator()
    concatenator("Not default;")
    concatenator(count = 7)
    complexFunction("Test inner call; ")
    println(anotherComplexFunction())
}


