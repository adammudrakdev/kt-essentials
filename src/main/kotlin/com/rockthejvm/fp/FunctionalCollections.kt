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

    fun demoSets() {
        val numbers = setOf(1, 2, 3, 4, 5)

        //check whether all set is true to predicate
        val lessThanTen = numbers.all { it < 10 }
        val noneBiggerThanTen = numbers.none { it > 10 }

        println(lessThanTen)
        println(noneBiggerThanTen)
    }

    fun demoMaps() {
        val phonebook = mapOf("Adam" to 123, "Iuliia" to 456, "Or" to 789)

        //filter keys
        val letterA = phonebook.filterKeys { it.startsWith("A") }

        //map values
        val addSuffix = phonebook
            .mapValues { pair -> pair.value * 10}
            .mapValues { pair -> pair.key + pair.value }

        println(addSuffix)

        val phonebookWithDefault = phonebook.withDefault { key ->
            println("No entry for $key, returning default")
            -9000
        }

        println(phonebookWithDefault.getValue("RANDOM"))
    }


    @JvmStatic
    fun main(args: Array<String>) {
        //practice
        //ex 1
        val testList: List<String> = listOf("I", "want", "to", "test", "lambda")
        val getListOfLengths = { l: List<String> -> l.map { s -> s.length } }
        println(getListOfLengths(testList))

        //ex 2
        val numbers1 = listOf(1, 2, 3, 4, 5)
        val numbers2 = listOf(2, 4, 6, 8, 10)
        val getSumOfTwoLists = { list1: List<Int>, list2: List<Int> -> list1.zip(list2).map { it.first + it.second } }
        val getSumOfTwoListsV2 = { list1: List<Int>, list2: List<Int> -> list1.zip(list2) { a, b -> a + b } }
        println(getSumOfTwoLists(numbers1, numbers2))
        println(getSumOfTwoListsV2(numbers1, numbers2))

        //ex 3
        val mixLists = { list1: List<Any>, list2: List<Any> -> list1.flatMap {
            el1 -> list2.map { "$el1-$it" }
        }}
        println(mixLists(testList, numbers1))

        //ex 4
        val listToStringFold = { list: List<String> -> list.fold(""){ a, b -> a + b } }
        val listToStringReduce = { list: List<String> -> list.reduce { a, b -> a + b } }
        println(listToStringReduce(testList))
        println(listToStringFold(testList))

        //ex 5
        val stringRepeater = { string: String, n: Int -> string.repeat(n)}
        println(stringRepeater("Kotlin", 5))
    }
}