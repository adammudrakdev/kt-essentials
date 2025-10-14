package com.rockthejvm.fp

object ScopeFunctions {
    fun obtainExternalList() = listOf(1, 2, 3, 4, 5)

    //let
    fun demoLet() {
        obtainExternalList().let { numbers ->
            val tenXNumbers = numbers.map { n -> n * 10 }
            println("The 10x values are $tenXNumbers")
            val sumNumbers = numbers.reduce { a, b -> a + b }
            println("The sum values are $sumNumbers")
        }

        println(obtainExternalList()
            .filter { n -> n % 2 == 0 }
            .map { n -> n * 10 }
            .let { list -> println("The avg of even * 10 elems is ${list.sum() / (list.size + 1)}") })

    }

    //run
    data class Person(var name: String, var age: Int) {
        override fun toString(): String = "$name $age"
    }

    fun demoRun() {
        val masterYoda = Person("Master Yoda", 800)
        masterYoda.age = 850
        masterYoda.name = "Yoda the Master Jedi"
        var result = "${masterYoda.name} (${masterYoda.age})"

        val result_v2 = masterYoda.run {
            // with run, you have auto "this" access
            age = 1000
            name = "Yoda the Master Sith"
            "$name ($age)"
        }

        println(result)
        println(result_v2)
    }

    //with = run, but that is not an extension function
    data class GamingChannel(val playerA: String, val playerB: String, var open: Boolean) {
        fun msg(content: String) = println("[$playerA][to $playerB] $content")
    }

    fun demoWith() {
        val channel = GamingChannel("Alice", "Bob", true)
        channel.msg("build up your forces!")
        channel.msg("attack here")
        channel.open = true

        // with useful when we use "resources"
        with(channel) { // = channel.run
            msg("build up your forces!")
            msg("attack here")
            open = true
        }
    }

    //apply = run, but it RETURNS the object that you're processing
    fun demoApply() {
        val alice = Person("Alice", 23)
        alice.name = "Alice in Wonderland"
        alice.age = 24
        println("${alice.name} ${alice.age}")

        val trueAlice: Person = alice.apply {
            name = "Alice in Asylum"
            age = 30
        }
        println(trueAlice)
    }

    //also - same as let, but returns UNIT
    //useful for side effects
    fun demoAlso() {
        obtainExternalList()
            .filter { it % 2 == 0 }
            .map { it % 10 }
            .also { list -> println("The avg of even * 10 elems is ${list.sum() / (list.size + 1)}") }
            .forEach { println(it) }
    }

    @JvmStatic
    fun main(args: Array<String>) {
        demoApply()
    }
}