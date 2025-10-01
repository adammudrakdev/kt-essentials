package com.rockthejvm.oop

object MySingleton {
    val aProperty = 42

    fun aMethod(arg: Int): Int {
        println("Hello from MySingleton: $arg")
        return aProperty + arg
    }
}

object ObjectsCompanions {
    class Guitar(val nStrings: Int, val type: String) {
        fun play() {
            println("$type guitar with $nStrings string playing")
        }

        companion object {
            const val HAS_STRINGS = true
            fun createSimpleGuitar(type: String): Guitar = Guitar(6, type)
        }
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val gibson = Guitar(6, "electric")
        gibson.play()

        val simpleGuitar = Guitar.createSimpleGuitar("acoustic")
        simpleGuitar.play()
        println(Guitar.HAS_STRINGS)
    }
}

fun main() {
    val singleton = MySingleton
    val anotherSingleton = MySingleton

    println(singleton == anotherSingleton && singleton === anotherSingleton)
    println(singleton.aMethod(50))
    println(MySingleton.aMethod(55))

}