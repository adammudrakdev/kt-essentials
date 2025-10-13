package com.rockthejvm.oopfun

object Enums {

    sealed interface PermissionNaive {
        companion object {
            data object READ: PermissionNaive
            data object WRITE: PermissionNaive
            data object EXECUTION: PermissionNaive
            data object NONE: PermissionNaive
        }
    }

    //final - uninheritable
    enum class Permission {
        READ, WRITE, EXECUTE, NONE;
        // - semicolon is needed to close values before you add properties and functions
        val size = 4
        fun openDocument() {
            when(this) {
                READ -> println("opening document...")
                else -> throw IllegalAccessException("Reading not allowed")
            }
        }
    }

    enum class PermissionIntMask(int: Int) {
        READ(0), WRITE(1), EXECUTE(2), NONE(3)
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val readPerms = Permission.READ
        val readPerm2 = Permission.READ
        println(readPerms == readPerm2)
        println(readPerms === readPerm2)
        println(Permission.valueOf("READ") == readPerms)

        println("VALUES:")
        Permission.values().forEach { println(it) }

        println("ORDINAL")
        println(readPerms.ordinal)
        println(Permission.entries.size) // size of enum
        println(readPerms.size) // size of parental enum

        readPerms.openDocument()
        Permission.WRITE.openDocument()
    }
}