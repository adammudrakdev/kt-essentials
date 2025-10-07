package com.rockthejvm.oop

import kotlin.jvm.Throws

object Exceptions {
    fun maybeString(): String? = null

    fun demoExceptions() {
        // code that might fail
        // val division = 42 / 0 // ArithmeticException
        val nullable: String? = maybeString()
        val theString = nullable!! // NullPointerException
    }

    //throwing an exception
    class Person private constructor(val name: String?, val age: Int) {
        companion object {
            @Throws(IllegalArgumentException::class, NullPointerException::class)
            fun create(name: String?, age: Int): Person {
                if (name == null) {
                    throw NullPointerException("name must not be null")
                }
                if (age < 0) {
                    throw IllegalArgumentException("age must be >= 0")
                }
                return Person(name, age)
            }
        }
    }

    //catching exceptions
    val maybePerson: Person = try {
        Person.create("Adam", -18)
    } catch (e: IllegalArgumentException) {
        Person.create(name = "PositiveAdam", age = 18)
    } catch (e: NullPointerException) {
        Person.create(name = "NotNullAdam", age = 18)
    } finally {
        println("No matter what - I run")
    }

    class MyException(val count: Int) : RuntimeException("Something went wrong") {
        fun demoMyException() {
            throw MyException(4)
        }
    }

    @JvmStatic
    fun main(args: Array<String>) {
        demoExceptions()
    }
}