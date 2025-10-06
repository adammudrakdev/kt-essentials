package com.rockthejvm.practice

import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

class Image (val buffImage: BufferedImage) {
    val width = buffImage.width
    val height = buffImage.height

    fun save(path: String) =
        ImageIO.write(buffImage, "JPG", File(path))

    fun saveResources(path: String) =
        save("src/main/resources/$path")

    companion object {
        fun black(width: Int, height: Int): Image {
            val buffImage = BufferedImage(width, height, BufferedImage.TYPE_INT_RGB)
            val pixels = IntArray(width * height) { 0 }
            buffImage.setRGB(0,0, width, height, pixels, 0, width)
            return Image(buffImage)
        }

        fun load(path: String) = ImageIO.read(File(path))

        fun loadResource(path: String) =
            load("src/main/resources/$path")
    }
}

object ImagePlayground {
    @JvmStatic
    fun main(args: Array<String>) {
        Image.black(100, 100).saveResources("black.jpg")
    }
}
