package com.rockthejvm.practice

import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

const val RED_BYTE_LEFT_OFFSET = 16
const val GREEN_BYTE_LEFT_OFFSET = 8


class Color(val red: Int, val green: Int, val blue: Int) {
    val resultColor = mix()

    fun checkIntegrity(colorRange: Int) {
        if (colorRange !in 0..255) throw RuntimeException("Value $colorRange is not in range")
    }

    fun mix(): Int {
        checkIntegrity(this.red)
        checkIntegrity(this.green)
        checkIntegrity(this.blue)

        return this.red.shl(RED_BYTE_LEFT_OFFSET) or this.green.shl(GREEN_BYTE_LEFT_OFFSET) or this.blue
    }

    fun drawColor(width: Int, height: Int, color: String = "new_color") {
        val image = BufferedImage(width, height, BufferedImage.TYPE_INT_RGB)
        val pixels = IntArray(width * height) { this.resultColor }
        image.setRGB(0,0, width, height, pixels, 0, width)
        ImageIO.write(image, "JPG", File("src/main/resources/${color}_${(Math.random() * 1000).toInt()}.jpg"))
    }
}



fun main() {
    val orange = Color(255, 130, 0)
    orange.drawColor(300, 300, "orange")

    val yellow = Color(255, 255, 0)
    yellow.drawColor(300, 300, "yellow")

    val purple = Color(255, 0, 255)
    purple.drawColor(300, 300, "purple")
}