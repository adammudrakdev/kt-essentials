package com.rockthejvm.fp

object FunctionalCollections {
    fun concatenate(n: Int, s: String): String =
        if (n <= 0) ""
        else s + concatenate(n - 1, s)

    fun demoLists() {
        val numbers = listOf(2, 4, 6, 8, 1, 2, 3, 4, 5)

        println(numbers.map { it * 10 })

        println(numbers.map { x -> concatenate(x, "Kotlin")})

        println(numbers.filter { it % 2 == 0 })

        numbers.forEach { println(it) }

        val expandedList = numbers.map { x -> (1 .. x).toList() }
        println(expandedList)

        val expandedFlatList = numbers.flatMap { x -> (1 .. x).toList() }
        println(expandedFlatList)

        //with initial value
        val numbersSum = numbers.fold(0) { a, b -> a + b }
        println(numbersSum)

        //without inital value
        val numbersSumV2 = numbers.reduce { a, b -> a + b }
        println(numbersSumV2)

        //processing with predicates
        val firstEven = numbers.find { it % 2 == 0 }

        println("firstEven $firstEven")

        val evenPrefix = numbers.takeWhile { it % 2 == 0 } // takes while true, then breaks
        println(evenPrefix)

        val evenCount = numbers.count { it % 2 == 0}
        val evenCount_v2 = numbers
            .filter { x -> x % 2 == 0 }
            .size

        println(evenCount == evenCount_v2)

        val stringRep = numbers.joinToString("|", "{", "}") { x -> x.toString() }
        println(stringRep)
    }


    @JvmStatic
    fun main(args: Array<String>) {
        demoLists()
    }
}