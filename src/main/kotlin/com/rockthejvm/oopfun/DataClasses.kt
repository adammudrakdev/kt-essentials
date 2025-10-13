package com.rockthejvm.oopfun

object DataClasses {
    class CityNaive(val name: String, val country: String, val population: Int)

    //equals
    //hashCode
    //toString
    //copy
    //destructuring
    //all of the above with no need to override
    //ALL args must be properties (val \ var)
    //Data classes must have AT LEAST one property
    //You CANNOT inherit data class
    data class City(val name: String, val country: String, val population: Int)

    sealed interface Message

    //these hold data
    data class Join(val player: String): Message
    data class Ping(val from: String, val to: String): Message
    data class Exit(val player: String): Message

    //this does not
    data object TerminateGame: Message


    @JvmStatic
    fun main(args: Array<String>) {
        val bucharest = City("Bucharest", "Romania", 2_000_000)
        val bucharest2 = City("Bucharest", "Romania", 2_000_000)
        val bucharest3 = bucharest.copy()
        val bucharest4 = bucharest.copy(population = 2_500_000)

        println(bucharest)
        println(bucharest == bucharest2)
        println(bucharest == bucharest3)
        println(bucharest == bucharest4)

        val (name, country, population) = bucharest
        val (name4, country4, population4) = bucharest4

        println("$name, $country, $population")
        println("$name4, $country4, $population4")
    }
}