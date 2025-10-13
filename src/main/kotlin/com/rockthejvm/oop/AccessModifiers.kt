package com.rockthejvm.oop

object AccessModifiers {
    open class Person(open val name: String) {
        protected fun sayHi() = "Hi, I'm $name"

        private fun watchNetflix(): String = "I'm watching Netflix."
    }

    class Kid(override val name: String, age: Int) : Person(name) {
        fun greetPolitely(): String =
            sayHi() + "I love to play!"
    }

    class KidWithParents(override val name: String, val age: Int,
                         val mom: Person, val dad: Person) : Person(name) {
        fun everyoneIntroduceThemselves(): String =
            "Hi, I'm $name. Here are my parents! ${mom.name} ${dad.name}"
    }

    val aPerson = Person("Adam")

    class MyService private constructor(url: String) {
        companion object {
            fun local(url: String): MyService {
                return MyService(url)
            }
        }
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val alice = Person("Alice")
        val bob = Person("Bob")
        val kid = KidWithParents("Dennis", 5, alice, bob)
        val myService = MyService.local("http://localhost:8080")
    }
}