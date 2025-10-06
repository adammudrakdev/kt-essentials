package com.rockthejvm.oop

object AbstractClassesInterfaces {
    open class Animal

    // abstract classes / properties / methods are auto open and auto non-final
    abstract class Plant(specificName: String) { // class with possible abstract properties and methods
        abstract val maxHeight: Int // property with no value
        abstract fun grow(): String // method signature with no impl
        // can define "regular" properties / methods
        val growthMechanism: String = "photosynthesis"
    }

    class Strawberry: Plant("fragaria") {
        override val maxHeight: Int = 100
        override fun grow(): String = "nice tasty strawberries"
    }

    // val myPlant = Plant("rosa") // abstract cannot be instantiated*

    // interfaces are auto abstract and open
    interface Carnivore {
        fun eat(animal: Animal): String = "eating this $animal"
        val preferredMeal: String
            get() = "meat" // may only provide an impl if the property has no backing field
        // can provide default properties / methods
    }

    interface Herbivore {
        fun eat(plant: Plant): String = "eating this $plant"
    }

    // inheritance model in Kotlin: extend ONE class, implement MANY interfaces
    class Crocodile: Animal(), Carnivore {
        override val preferredMeal: String = "gazelle"
        override fun eat(animal: Animal): String = "eating $animal"
    }

    class Human: Carnivore, Herbivore {
        override val preferredMeal: String = "sugar"
        override fun eat(plant: Plant): String = "eating $plant"
        override fun eat(animal: Animal): String = "hopefully this makes a good steak!"
    }

    // interface can extend another interface
    interface Omnivore: Carnivore, Herbivore

    abstract class Human_V2: Omnivore {
        // what if 2 interfaces have the same methodf
    }

    interface Instrument {
        fun play(): String
    }

    interface Game {
        fun play(): String
    }

    class GuitarApp: Instrument, Game {
        override fun play(): String = "Playing Guitar"
    }

    @JvmStatic
    fun main(args: Array<String>) {

    }
}