package com.rockthejvm.practice

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
    fun process(image: Image): Image

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
    override fun process(image: Image): Image {
        try {
            return image.crop(x, y, width, height)!!
        } catch (_: Exception) {
            println("Error: coordinates are out of bounds. Max coordinates: ${image.width} X ${image.height}")
            return image
        }
    }
}

class Blend(val fgImage: Image, val blendMode: BlendMode): Transformation {
    override fun process(bgImage: Image): Image {
        if (fgImage.width != bgImage.width || fgImage.height != bgImage.height) {
            println("Error: pictures must be of the exact same size")
            return bgImage
        }
        val black: Image = Image.black(bgImage.width, bgImage.height)
        var blackX = 0
        for (countX in 0 ..< bgImage.width) {
            var blackY = 0
            for (countY in 0 ..< bgImage.height) {
                val fgPixel: Int = fgImage.getBuffImage().getRGB(countX, countY)
                val bgPixel: Int = bgImage.getBuffImage().getRGB(countX, countY)
                val mixedColor = blendMode.combine(
                    Color.fromHex(fgPixel), Color.fromHex(bgPixel))
                black.getBuffImage().setRGB(blackX, blackY, mixedColor.resultColor)
                blackY++
            }
            blackX++
        }
        return black
    }


}

object Noop: Transformation {
    override fun process(image: Image): Image {
        return image
    }

}