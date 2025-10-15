package com.rockthejvm.practice

import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

const val RED_BYTE_OFFSET = 16
const val GREEN_BYTE_OFFSET = 8


class Color(val red: Int, val green: Int, val blue: Int) {
    val resultColor = mix()

    fun checkIntegrity(colorRange: Int) {
        if (colorRange !in 0..255) throw RuntimeException("Value $colorRange is not in range")
    }

    fun mix(): Int {
        checkIntegrity(this.red)
        checkIntegrity(this.green)
        checkIntegrity(this.blue)

        return this.red.shl(RED_BYTE_OFFSET) or this.green.shl(GREEN_BYTE_OFFSET) or this.blue
    }

    fun drawColor(width: Int, height: Int, color: String = "new_color", resultColor: Int = this.resultColor) {
        val image = BufferedImage(width, height, BufferedImage.TYPE_INT_RGB)
        val pixels = IntArray(width * height) { resultColor }
        image.setRGB(0,0, width, height, pixels, 0, width)
        ImageIO.write(image, "JPG", File("src/main/resources/colors/${color}_${(Math.random() * 1000).toInt()}.jpg"))
    }

    fun clampColour(v: Int) =
        if (v <=0) 0
        else if (v >= 255) 255
        else v

    operator fun plus(other: Color): Color =
        Color(clampColour(red + other.red),
            clampColour(green + other.green),
            clampColour(blue + other.blue))

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Color

        if (red != other.red) return false
        if (green != other.green) return false
        if (blue != other.blue) return false
        if (resultColor != other.resultColor) return false

        return true
    }

    override fun hashCode(): Int {
        var result = red
        result = 31 * result + green
        result = 31 * result + blue
        result = 31 * result + resultColor
        return result
    }

    companion object {
        val BLACK = Color(0, 0, 0)
        val WHITE = Color(255, 255, 255)
        val RED = Color(255, 0, 0)
        val GREEN = Color(0, 255, 0)
        val BLUE = Color(0, 0, 255)

        val YELLOW = Color(255, 255, 0)
        val MAGENTA = Color(255, 0, 255)
        val CYAN = Color(0, 255, 255)

        val GRAY = Color(128, 128, 128)

        fun fromHex(arg: Int): Color {
            val red = (arg and 0xFF0000) shr RED_BYTE_OFFSET
            val green = (arg and 0xFF00) shr GREEN_BYTE_OFFSET
            val blue = arg and 0xFF
            return Color(red, green, blue)
        }
    }
}
