package com.rockthejvm.oop

object Nullables {
    class Developer(val name: String, val favLanguage: String = "Kotlin") {
        fun writeCode(code: String = "") {
            println("$name writing code in $favLanguage: $code")
        }

        override fun toString(): String {
            return "Developer(name='$name', favLanguage='$favLanguage')"
        }
    }

    fun createDeveloper(name: String): Developer? =
        if (name.isNotEmpty()) Developer(name)
        else null

    fun makeDeveloperWriteCode(dev: Developer?, code: String) {
        if (dev != null) dev.writeCode(code)
        else println("Error developer is null")
    }

    fun makeDeveloperWriteCode_v2(dev: Developer?, code: String) {
        dev?.writeCode(code) ?: println("Error developer is null")
    }

    //val maybeDeveloper: Developer = null - compile error
    val nullButMaybeDev: Developer? = null //nullable
    val maybeNullDev: Developer? = createDeveloper("Master Yoda") //nullable
    val notNullFavLan: String = maybeNullDev!!.favLanguage // I am sure it's not null
    val maybeNullName: String? = maybeNullDev?.name // I am NOT sure that it's not null
    val defaultDeveloper: Developer = nullButMaybeDev ?: Developer("John Doe", "JS")
    // I am NOT sure that it's not null, so I give default value

    val something: Any = 42
    val anInt: Int = something as Int + 50 // I am sure something is int
    val maybeInt: Int? = (something as? Int)?.plus(55) // If it is an int, then sum

    @JvmStatic
    fun main(args: Array<String>) {
        makeDeveloperWriteCode(maybeNullDev, "val x = 42")
        makeDeveloperWriteCode(null, "val x = 42")

        println(notNullFavLan)
        println(maybeNullName)

        println(defaultDeveloper)
        println(maybeNullDev)

        makeDeveloperWriteCode_v2(null, "val x = 42")

        println(anInt)
        println(maybeInt)
    }
}