package com.rockthejvm.practice

import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.assertThrows
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotSame
import kotlin.test.assertTrue
import kotlin.test.fail

class ColorTestingDemo {
    private val black = Color(0, 0, 0)
    private val red = Color(255, 0, 0)
    private val green = Color(0, 255, 0)
    private val blue = Color(0, 0, 255)
    private val yellow = Color(255, 255, 0)

    private lateinit var colors: List<Color>

    val meaningOfLife: Int = 42

    @BeforeEach
    fun individualSetup() {
        println("individual setup")
        colors = listOf(red, green, blue, yellow)
    }

    @AfterEach
    fun individualCleanup() {
        println("individual cleanup")
        colors = listOf()
    }

    @Test
    fun simpleTest() {
        assertTrue { meaningOfLife + 2 == 44 }
    }

    @Test
    fun anotherSimpleTest() {
        assertTrue { meaningOfLife + 2 != 43 }
    }

    @Test
    fun testPlus() {
        println("Testing the + operator on colours")
        assertTrue {
            red + green == yellow
        }
        assertEquals(red + green, yellow, "Operator + should combine color channels")
        //assertSame(red + green, yellow) // exact same instance
        assertNotSame(red + green, yellow)

        assertThrows<RuntimeException> {
            if (42 > 0) {
                throw RuntimeException("Meaning of life should throw an error when compared to 0")
            }
        }

        if (red + green != yellow) {
            fail("The operator + doesn't work")
        }
    }

    companion object {
        @BeforeAll
        @JvmStatic
        fun suiteSetup() {
            println("Suite setting up")
        }

        @AfterAll
        @JvmStatic
        fun suiteCleanup() {
            println("Suite cleanup")
        }
    }
}