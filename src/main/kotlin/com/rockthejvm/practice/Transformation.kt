package com.rockthejvm.practice

import com.rockthejvm.practice.Image.Companion.black
import java.awt.image.BufferedImage

/*
    1. Create an interface Transformation with a single 'process' method
        taking an Image and returning another Image;

    2. Create 3 subclasses of transformation:
        - Crop - x, y, w, h as constructor args;
        - Blend = fgImage, blendMode as constructor args;
        - Noop - does nothing
        - stub the process method e.g. printing something
 */

interface Transformation {
    fun process(image: Image): Image?

    companion object {
        fun parse(transformationType: String): Transformation {
            if (transformationType.startsWith("crop")) {
                val stringArray = transformationType.split(" ")
                val x: Int = stringArray[1].toInt()
                val y: Int = stringArray[2].toInt()
                val w: Int = stringArray[3].toInt()
                val h: Int = stringArray[4].toInt()
                return Crop(x, y, w, h)
            } else if (transformationType.startsWith("blend")) {
                val stringArray = transformationType.split(" ")
                val imageName = stringArray[1]
                val blendMode = stringArray[2]
                return Blend(Image(Image.loadResource(imageName)),
                        BlendMode.parse(blendMode))
            } else {
                return Noop
            }
        }
    }
}

class Crop(val x: Int, val y: Int, val width: Int, val height: Int) : Transformation {
    override fun process(image: Image): Image? {
        if (x < 0 || x >= this.width || y < 0 || y >= this.height) return null
        if (x + width > this.width || y + height > this.height) return null
        val processedImage = black(width, height)
        var processedImageX = 0
        for (countX in x ..< x + width) {
            var processedImageY = 0
            for (countY in y ..< y + height) {
                val currentPixel = Image.getBuffImage(image).getRGB(countX, countY)
                Image.getBuffImage(image).setRGB(processedImageX, processedImageY, currentPixel)
                processedImageY++
            }
            processedImageX++
        }
        return processedImage
    }
}

class Blend(val foregroundImage: Image?, val blendMode: BlendMode?): Transformation {
    override fun process(bgImage: Image): Image? {
        return bgImage
    }


}

object Noop: Transformation {
    override fun process(image: Image): Image {
        return image
    }

}