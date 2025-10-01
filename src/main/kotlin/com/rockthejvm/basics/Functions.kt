package com.rockthejvm.basics

import kotlin.math.sqrt

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

    println(greet())
    println(greet("Adam"))
    println(greet(age = 1))
    println(greet("Ivan", 40))
    println(factorial(0))
    println(fibonacci(7))
    println(recursiveFibonacci(7) == fibonacci(7))
    println(isPrime(2003))
    println(recursiveIsPrime(2003))
}

//HW

fun greet(name: String = "John", age: Int = 18): String =
    "Hello, my name is $name and I am $age ${if (age == 1) "year" else "years"} old."

fun factorial(n: Int): Int {
    if (n == 0) {
        return -1
    }
    if (n == 1) {
        return 1
    }
    return n * factorial(n - 1)
}

fun fibonacci(n: Int): Int {
    if (n == 0) {
        return -1
    }
    if (n == 1) {
        return 1
    }
    var first = 0
    var second = 1
    var third = first + second
    for (i in 1..< n) {
        val tempSecond = second
        second = third
        first = tempSecond
        third = first + second
    }
    return third
}

fun recursiveFibonacci(n: Int): Int =
    if (n <= 0) -1
    else if (n == 1) 1
    else if (n == 2) 2
    else recursiveFibonacci(n - 1) + recursiveFibonacci(n - 2)

fun isPrime(n: Int): Boolean {
    if (n < 2) {
        return false
    }
    if (n == 2) {
        return true
    }
    var counter = 1

    while (counter++ < sqrt(n.toDouble())) {
        if (n % counter == 0) {
            return false
        }
    }
    return true
}

fun recursiveIsPrime(n: Int, d: Int = 2): Boolean =
    if (n % d == 0) false
    else if (d > n / 2) true
    else recursiveIsPrime(n, d + 1)