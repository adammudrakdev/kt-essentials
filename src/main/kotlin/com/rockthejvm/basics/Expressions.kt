package com.rockthejvm.basics

fun main() {
    println("OPERATORS")
    val bitwiseLeft = 2 shl 2
    // 2 = 00000000 00000000 00000000 00000010
    // Shift left by 2:
    //     00000000 00000000 00000000 00001000 = 8
    // Explanation: shifting left multiplies the number by 2^n (here, 2^2 = 4 → 2*4 = 8)

    val bitwiseRight = 3 shr 1
    // 3 = 00000000 00000000 00000000 00000011
    // Shift right by 1:
    //     00000000 00000000 00000000 00000001 = 1
    // Explanation: shifting right divides the number by 2^n (integer division)

    val unsignedBitwiseRight = -8 ushr 31
    // -8 in 32-bit = 11111111 11111111 11111111 11111000
    // Unsigned shift right by 31:
    //     00000000 00000000 00000000 00000001 = 1
    // Explanation: unlike `shr`, `ushr` always fills left bits with 0

    val and = 3 and 1
    // 3 = 00000000 00000000 00000000 00000011
    // 1 = 00000000 00000000 00000000 00000001
    // AND → 00000000 00000000 00000000 00000001 = 1
    // Explanation: AND returns 1 only if both bits are 1

    val or = 3 or 1
    // 3 = 00000000 00000000 00000000 00000011
    // 1 = 00000000 00000000 00000000 00000001
    // OR  → 00000000 00000000 00000000 00000011 = 3
    // Explanation: OR returns 1 if at least one bit is 1

    val xor = 3 xor 1
    // 3 = 00000000 00000000 00000000 00000011
    // 1 = 00000000 00000000 00000000 00000001
    // XOR → 00000000 00000000 00000000 00000010 = 2
    // Explanation: XOR returns 1 if bits are different

    val inv = 3.inv()
    // 3  = 00000000 00000000 00000000 00000011
    // INV= 11111111 11111111 11111111 11111100 = -4
    // Explanation: `inv()` flips every bit (1→0, 0→1).
    //              In two’s complement, this is equivalent to -(n+1).

    println("bitwiseLeft = $bitwiseLeft")
    println("bitwiseRight = $bitwiseRight")
    println("unsignedBitwiseRight = $unsignedBitwiseRight")
    println("and = $and")
    println("or = $or")
    println("xor = $xor")
    println("inv = $inv")

    println("COMPARE BY VALUE")
    val a = String("hello".toCharArray())
    val b = String("hello".toCharArray())

    println("a == b: ${a == b}")   // true → values are the same
    println("a != b: ${a != b}")   // false

    println("COMPARE BY REFERENCE")
    val a1 = String("hello".toCharArray())
    val b1 = String("hello".toCharArray())
    val c1 = a1

    println("a1 === b1: ${a1 === b1}")  // false → different objects in memory
    println("a1 === c1: ${a1 === c1}")  // true → both refer to the same object
    println("a1 !== b1: ${a1 !== b1}")  // true → a and b are NOT the same reference

    println("CONDITIONALS")
    println(if (2 + 2 == 4) "Four" else "Five")

    val value = 40
    val valueFromWhenExpression = when (value) {
        40 -> "Hello"
        else -> "Bye"
    }
    println("valueFromWhenExpression $valueFromWhenExpression")

    val valueToTestAgainst = 20
    val anotherValueFromWhenExpression = when {
        valueToTestAgainst <= 10 -> "SMALL"
        valueToTestAgainst >= 11 &&  valueToTestAgainst <= 20 -> "GOOD"
        else -> "GREAT"
    }
    println("anotherValueFromWhenExpression $anotherValueFromWhenExpression")

    println("TYPE CHECK WITH \"IS\"")
    val any: Any = '1'
    when (any) {
        is Int -> println(any + 5)
        is String -> println("Can't do math with $any")
        is Boolean -> println("It's a boolean alright $any")
        else -> println("Default")
    }

    println("FOR LOOPS")
    println("Inclusive")
    for (i in 1..10) {
        println("$i) Action $i;")
    }

    println("\nExclusive")
    for (i in 1..< 10) {
        println("$i) Action $i;")
    }
    println("\nExclusive \'until\'")
    for (i in 1 until 10) {
        println("$i) Action $i;")
    }

    println("\nInclusive with step")
    var counter = 1
    for (i in 1..10 step 2) {
        println("${counter++}) Action $i;")
    }

    println("\nBackwards with step")
    var anotherCounter = 1
    for (i in 10 downTo 1 step 2) {
        println("${anotherCounter++}) Action $i;")
    }

    println("\nElements in collection")
    val anArray = arrayOf(1, 2, 3, 4, 5)
    for (el in anArray) {
        println("Element in array: $el")
    }

    println("\nElements in collection with step")
    for (i in 0..< anArray.size step 2) {
        println("$i element in array: ${anArray[i]}")
    }

    println("WHILE LOOPS")
}
