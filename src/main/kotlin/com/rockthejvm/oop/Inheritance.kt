package com.rockthejvm.oop

object Inheritance {
    //Animal
    open class Animal {
        open fun eat() {
            print("Animal is eating...")
        }
    }

    class Dog: Animal() {
        override fun eat() {
            super.eat()
            print("Also, this animal is a dog...")
        }
    }

    //Person - observe how we need to provide a constructor for a parent class
    open class Person(open val name: String, open val age: Int)
    class Adult(override val name: String, override val age: Int, idCard: String): Person(name, age)

    // restrict inheritance with 'final' keyword



    val animal: Animal = Dog()

    @JvmStatic
    fun main(args: Array<String>) {
        animal.eat()
    }
}