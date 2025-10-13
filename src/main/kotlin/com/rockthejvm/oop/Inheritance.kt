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
    open class Travel(val destination: String) {
        final fun confirm(): String = "Congrats! You're going to $destination!"
    }

    open class Leisure {
        open fun confirmExperience(): String = "Chill"
    }

    open class Travel_V2(val destination: String): Leisure() {
        final override fun confirmExperience(): String {
            return "Congrats! You're going to $destination!"
        }
    }

    class SpecialTickets: Travel_V2("USA") {
        // override fun confirmExperience(): String = "Seeing Breaking Benjamin!"
        // stop inheritance!
    }

    sealed class ProtocolMessage(content: String) //auto opened

    class BeginningExchange(flag: String, contents: String): ProtocolMessage(contents)
    class Exchange(sender: String, receiver: String, contents: String): ProtocolMessage(contents)
    object EndExchange : ProtocolMessage("")


    val animal: Animal = Dog()

    @JvmStatic
    fun main(args: Array<String>) {
        animal.eat()
    }
}