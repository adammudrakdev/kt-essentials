package com.rockthejvm.oopfun

import kotlin.math.sqrt

object SpecialMethods {
    class Person(val name: String, val age: Int) {
        override fun equals(other: Any?): Boolean = when (other) {
            is Person -> name == other.name && age == other.age
            else -> false
        }

        override fun hashCode(): Int =
            name.hashCode() * 31 + age

        override fun toString(): String =
            "Person($name, $age)"

        infix fun likes(movie: String) =
            "$name says: I LOVE $movie!"

        operator fun compareTo(another: Person): Int =
            this.age - another.age
    }

    class ComplexNumber(var x: Double, var y: Double) {
        //operator overloading - plus, minus, times, div, rem
        override fun toString(): String =
            "($x, $y)"
        operator fun plus(other: ComplexNumber) =
            ComplexNumber(x + other.x, y + other.y)

        operator fun plus(other: Double) =
            ComplexNumber(x + other, y)

        //compound operators - must return UNIT
        //plusAssign, minusAssign,...
        operator fun plusAssign(number: Double): Unit {
            x += number
        }

        //access elements
        //cn[0], cn[1]
        operator fun get(index: Int): Double =
            when (index) {
                0 -> x
                1 -> y
                else -> throw IllegalArgumentException("Complex numbers only have 2 fields!")
            }

        operator fun get(name: String): Double =
            when (name) {
                "x" -> x
                "y" -> y
                else -> throw IllegalArgumentException("Complex numbers only have 2 fields!")
            }

        operator fun set(index: Int, value: Double) {
            when (index) {
                0 -> x = value
                1 -> y = value
                else -> throw IllegalArgumentException("Complex numbers only have 2 fields!")
            }
        }

        operator fun contains(value: Double): Boolean =
            x == value || y == value

        //x++ x--
        operator fun inc(): ComplexNumber = ComplexNumber(x + 1, y + 1)
        // -x, unary minus
        operator fun unaryMinus(): ComplexNumber = ComplexNumber(-x, -y)

        operator fun component1() = x
        operator fun component2() = y
        operator fun component3() = sqrt(x)

        operator fun invoke(origin: ComplexNumber): ComplexNumber =
            ComplexNumber(x - origin.x, y - origin.y)
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val adam1 = Person("Adam", 25)
        val adam2 = Person("Adam", 25)
        val eve = Person("Eve", 16)

        println(adam1 == adam2)
        println(adam1)
        println(adam1.likes("Forrest Gump")) //java like
        println(adam1 likes "Forrest Gump") //infix

        //operator
        val cn = ComplexNumber(1.2, 2.6)
        val acn = ComplexNumber(0.6, 2.9)
        println("PLUS")
        println(cn + acn)
        println(cn + 6.7)
        println(cn)
        println("PLUSASSIGN")
        cn += 8.9
        println(cn)
        println("INC")
        val newCn = cn.inc()
        println(newCn)
        println("UMINUS")
        println(-cn)
        println("UMINUS OVER UMINUS")
        println(-(cn.unaryMinus()))
        println(eve < adam1)
        println("ACCESS")
        println("cn[0] ${cn[0]} cn[1] ${cn[1]}")
        println("cn[x] ${cn["x"]} cn[y] ${cn["y"]}")
        println("Contains 2.6 in cn")
        println(2.6 in cn)

        val (x, y, z) = cn

        println("$x $y $z")

        println(cn(acn))
    }
}