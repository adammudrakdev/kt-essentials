package com.rockthejvm.fp

object SAMInterfaces {

    fun runNTimes(n: Int, runnable: Runnable) {
        for (i in 1 .. n) {
            runnable.run()
        }
    }

    fun runNTimesFunc(n: Int, func: () -> Unit) {
        (1 .. n).forEach { _ ->
            func()
        }
    }

    fun demoRunnable() {
        runNTimes(10, object: Runnable {
            override fun run() {
                println("This is a runnable")
            }
        })

        runNTimesFunc(10) { println("This is a func")}
    }

    fun demoRunnable_v2() {
        runNTimes(10) { println("This is a lambda of runnable")}
    }

    fun interface Transformer {
        fun transform(n: Int): Int
    }

    fun transformNTimes(seed: Int, n: Int, transformer: Transformer): Int =
        if (n <= 0) seed
        else transformNTimes(transformer.transform(seed), n - 1, transformer)

    @JvmStatic
    fun main(args: Array<String>) {
        demoRunnable()
        demoRunnable_v2()

        println(transformNTimes(0, 10) { it + 1})
    }
}