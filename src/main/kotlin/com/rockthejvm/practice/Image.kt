package com.rockthejvm.practice

import java.awt.Graphics
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

class Image (private val buffImage: BufferedImage) {
    fun getBuffImage() = buffImage

    val width = buffImage.width
    val height = buffImage.height

    fun save(path: String) =
        ImageIO.write(buffImage, "JPG", File(path))

    fun saveResources(path: String) =
        save("src/main/resources/$path.jpg")

    fun draw(g: Graphics) {
        g.drawImage(buffImage, 0, 0, null)
    }

    fun crop(x: Int, y: Int, width: Int, height: Int): Image? {
        if (x < 0 || x >= this.width || y < 0 || y >= this.height) return null
        if (width < 0 || x + width > this.width || height < 0 || y + height > this.height) return null
        val black = black(width, height)
        var blackX = 0
        for (countX in x ..< x + width) {
            var blackY = 0
            for (countY in y ..< y + height) {
                val currentPixel = buffImage.getRGB(countX, countY)
                black.buffImage.setRGB(blackX, blackY, currentPixel)
                blackY++
            }
            blackX++
        }

        return black
    }

    companion object {
        fun black(width: Int, height: Int): Image {
            val buffImage = BufferedImage(width, height, BufferedImage.TYPE_INT_RGB)
            val pixels = IntArray(width * height) { 0 }
            buffImage.setRGB(0,0, width, height, pixels, 0, width)
            return Image(buffImage)
        }

        fun load(path: String): BufferedImage = ImageIO.read(File(path))

        fun loadResource(path: String) =
            load("src/main/resources/$path.jpg")
    }
}
