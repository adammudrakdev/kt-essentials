package com.rockthejvm.oopfun

object NestedInnerClasses {
    class Outer {
        val aProp = 4

        //Depends on type, not on instance
        class Nested {
            val nestedProp = 42
            // val aPropCopy = aProp // not possible
        }

        //Depends on instance, not on type
        inner class Inner{
            val innerProp = aProp + 10
            //this with qualifier
            val outerInstance: Outer = this@Outer
            val innerInstance: Inner = this@Inner
        }
    }

    fun demoClasses() {
        val nested = Outer.Nested()
        println(nested.nestedProp)
        // val inner = Outer.Inner() // compile error
        val outer: Outer = Outer()
        val inner: Outer.Inner = outer.Inner()
        println(inner.innerProp)
    }

    // Nested classes are useful when they are tied to definition(concept) of particular service
    interface MyProtocol {
        sealed class Message
        data class Start(val nPlayers: Int): Message()
        data class GameEvent(val type: String, val playerId: String): Message()
        //......
    }

    class MyPermissionsService {

        // only relevant in THIS INSTANCE
        open inner class Role(name: String)
        inner class Admin: Role("ADMIN")
        inner class Moderator: Role("MODERATOR")
        inner class User: Role("USER")
    }

    // Inner classes are useful when they are tied to implementation of particular service

    @JvmStatic
    fun main(args: Array<String>) {
        demoClasses()
    }
}