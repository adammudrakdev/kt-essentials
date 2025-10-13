package com.rockthejvm.oopfun

object AnonymousClasses {

    abstract class Plant {
        abstract fun grow(): String
    }

    val weirdPlant = object:Plant() {
        override fun grow(): String = "weird flowers that nobody has seen!"
    }

    open class Instructor(val type: String) {
        open fun encourage(name: String) =
            "Come on $name, you can do it!"
    }

    val dad = object:Instructor("DAD") {
        override fun encourage(name: String): String =
            "Just do it $name! Are you stupid?"
    }

    @JvmStatic
    fun main(args: Array<String>) {
        print(weirdPlant.grow())
        print(dad.encourage("Adam "))
    }
}