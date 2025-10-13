package com.rockthejvm.fp

object FunctionValues {
    interface Transformation { //it "is" a function
        operator fun invoke(n: Int): Int
    }

    fun transformList(list: List<Int>, transformation: Transformation): List<Int> {
        val result = mutableListOf<Int>()
        for (n in list) {
            result.add(transformation(n))
        }
        return result
    }

    fun transformList_v2(list: List<Int>, transformation: (Int) -> Int): List<Int> {
        val result = mutableListOf<Int>()
        for (n in list) {
            result.add(transformation(n))
        }
        return result
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val numbers = listOf(1, 2, 3, 4, 5)

        val doubleFunction = object:Transformation { override fun invoke(n: Int): Int = n * 2 }
        val plusFiveFunction = object:Transformation { override fun invoke(n: Int): Int = n + 5 }
        val doubledNumbers = transformList(numbers, doubleFunction)
        val plusFiveNumbers = transformList(numbers, plusFiveFunction)
        //anonymous function
        val xTwentyFun = fun (x: Int): Int { return x * 20 }
        val xTwentyFun_V2 = { x: Int -> x * 10}

        println(numbers)
        println(doubledNumbers)
        println(plusFiveNumbers)
        println(transformList_v2(numbers, xTwentyFun))

        //functional programming - is about passing functions as args or returning them as results

        //one-arg lambdas
        val transformedNumbers = numbers
            .map { x: Int -> x + 3 }
            .map { x -> x - 2}
            .map { it * 100}
            .map { println(it) }

        // multi-arg lambdas

        val adder = { a: Int, b:Int -> a + b }
        val subtractor: (Int, Int) -> Int = { a, b -> a - b }

        println(adder(5, 7))
        println(subtractor(7, 5))
    }
}