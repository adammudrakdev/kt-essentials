package com.rockthejvm.practice

import com.rockthejvm.practice.Image.Companion.loadResource
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

class Image (val buffImage: BufferedImage) {
    val width = buffImage.width
    val height = buffImage.height

    fun save(path: String) =
        ImageIO.write(buffImage, "JPG", File(path))

    fun saveResources(path: String) =
        save("src/main/resources/$path.jpg")

    /*
    * 1. Check dimensions - return null if any dimensaion is invalid
    * 2. Create a black image of width * height
    * 3. Iterate through coords x ..< x + w, y ..< y + h
    *       - use buffImage.getRGB to get a pixel from original image;
    *       - use resultImage.buffImage.setRGB to set a pixel in the result;
    *       - calculate the coordinates;
    * 4. Return the result image
    * */
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

        fun load(path: String) = ImageIO.read(File(path))

        fun loadResource(path: String) =
            load("src/main/resources/$path")
    }
}

object ImagePlayground {
    @JvmStatic
    fun main(args: Array<String>) {
        val loadedImage = Image(loadResource("testImage.jpg"))
        val crop = loadedImage.crop(1000, 2000, 2000, 4000)
        crop?.saveResources("cropped")
    }
}
