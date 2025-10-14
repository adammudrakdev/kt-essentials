package com.rockthejvm.fp

object HOFs {
    val aHof: (Int, (Int) -> Int) -> Int = { x, func -> x + func(1) }
    val anotherHof: (Int) -> ((Int) -> Int) = { x -> { y -> y + 2 * x}}

    val four = aHof(2) { arg -> arg + 1 }
    val four_v2 = anotherHof(1)(2)

    //curried functions
    val superAdder: (Int) -> (Int) -> Int = { x -> { y -> x + y } }
    val add3: (Int) -> Int = superAdder(3)
    val seven = add3(4)

    //exercises
    fun<A, B, C> toCurry(f: (A, B) -> C): (A) -> (B) -> C = { x -> { y -> f(x, y)} }

    val regularAdder = { x: Int, y: Int -> x + y }
    val superAdder_v2 = toCurry(regularAdder)
    val seven_v2 = superAdder_v2(3)(4)

    fun<A, B, C> fromCurry(f: (A) -> (B) -> C): (A, B) -> C = { x, y -> f(x)(y) }
    val regularAdded_v2 = fromCurry(superAdder)
    val seven_v3 = regularAdded_v2(3, 4)

    fun <A, B, C> compose(f: (B) -> C, g: (A) -> B): (A) -> C = { x -> f(g(x)) }
    fun <A, B, C> composeReversed(f: (A) -> B, g: (B) -> C): (A) -> C = { x -> g(f(x)) }

    @JvmStatic
    fun main(args: Array<String>) {
        println(aHof(10) { arg -> arg })
        println(anotherHof(5)(67))
        println(four)
        println(four_v2)
        println(seven)
        println(seven_v2)
        println(seven_v3)

        println(compose(f = {x: Int  -> x * 2}, g = { x: Int -> x + 2})(2))
        println(composeReversed(f = {x: Int -> x * 2}, g = { x: Int -> x + 2})(2))
    }
}