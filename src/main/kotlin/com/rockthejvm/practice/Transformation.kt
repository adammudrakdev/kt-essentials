package com.rockthejvm.practice

interface Transformation {
    fun process(image: Image): Image

    companion object {
        fun parse(transformationType: String): Transformation {
            return if (transformationType.startsWith("crop")) {
                val stringArray = transformationType.split(" ")
                val x: Int = stringArray[1].toInt()
                val y: Int = stringArray[2].toInt()
                val w: Int = stringArray[3].toInt()
                val h: Int = stringArray[4].toInt()
                println("Check the cropped image...")
                Crop(x, y, w, h)
            } else if (transformationType.startsWith("blend")) {
                val stringArray = transformationType.split(" ")
                val imageName = stringArray[1]
                val blendMode = stringArray[2]
                println("Check the blended with $blendMode image...")
                Blend(Image(Image.loadResource(imageName)),
                        BlendMode.parse(blendMode))
            } else if (transformationType.startsWith("invert")) {
                println("Check the inverted image...")
                Invert
            } else if (transformationType.startsWith("grayscale")) {
                println("Check the grayscale image...")
                Grayscale
            } else {
                Noop
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
    override fun process(image: Image): Image {
        if (fgImage.width != image.width || fgImage.height != image.height) {
            println("Error: pictures must be of the exact same size")
            return image
        }
        val black: Image = Image.black(image.width, image.height)
        var blackX = 0
        for (countX in 0 ..< image.width) {
            var blackY = 0
            for (countY in 0 ..< image.height) {
                val fgPixel: Int = fgImage.getBuffImage().getRGB(countX, countY)
                val bgPixel: Int = image.getBuffImage().getRGB(countX, countY)
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

object Invert: Transformation {
    override fun process(image: Image): Image {
        val black: Image = Image.black(image.height, image.width)
        var blackX = 0
        for (countX in 0 ..< image.width) {
            var blackY = 0
            for (countY in 0 ..< image.height) {
                val currentPixel: Int = image.getBuffImage().getRGB(countX, countY)
                val color = Color.fromHex(255 - currentPixel)
                black.getBuffImage().setRGB(blackX, blackY, color.resultColor)
                blackY++
            }
            blackX++
        }
        return black
    }
}

object Grayscale: Transformation {
    override fun process(image: Image): Image {
        val black: Image = Image.black(image.height, image.width)
        var blackX = 0
        for (countX in 0 ..< image.width) {
            var blackY = 0
            for (countY in 0 ..< image.height) {
                val currentPixel: Int = image.getBuffImage().getRGB(countX, countY)
                val red = (currentPixel and 0xFF0000) shr RED_BYTE_OFFSET
                val green = (currentPixel and 0xFF00) shr GREEN_BYTE_OFFSET
                val blue = (currentPixel and 0xFF)
                val avg = (red + green + blue) / 3
                val color = Color(avg, avg,avg)
                black.getBuffImage().setRGB(blackX, blackY, color.resultColor)
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
