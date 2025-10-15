package com.rockthejvm.practice

const val COLOUR_CEILING = 255.0
const val INVERT_FACTOR = 1.0
const val DEFAULT_TRANSPARENCY_FACTOR = 0.5

interface BlendMode {
    fun combine(foregroundColor: Color, backgroundColor: Color): Color

    companion object {
        fun parse(mode: String): BlendMode {
            return when (mode) {
                "transparency" -> Transparency(DEFAULT_TRANSPARENCY_FACTOR)
                "multiply" -> Multiply
                "screen" -> Screen
                else -> NoBlend
            }
        }
    }
}

class Transparency(val factor: Double): BlendMode {
    override fun combine(foregroundColor: Color, backgroundColor: Color): Color =
            if (factor !in 0.0..1.0) throw RuntimeException("Factor must be in [0.0, 1.0]")
            else Color(
                (foregroundColor.red.times(factor)).toInt()
                    .plus(backgroundColor.red.times(INVERT_FACTOR.minus(factor))).toInt(),
                (foregroundColor.green.times(factor)).toInt()
                    .plus(backgroundColor.green.times(INVERT_FACTOR.minus(factor))).toInt(),
                (foregroundColor.blue.times(factor)).toInt()
                    .plus(backgroundColor.blue.times(INVERT_FACTOR.minus(factor))).toInt())
}

object Multiply: BlendMode {
    override fun combine(foregroundColor: Color, backgroundColor: Color): Color =
        Color(((foregroundColor.red.times(backgroundColor.red)).div(COLOUR_CEILING)).toInt(),
            ((foregroundColor.green.times(backgroundColor.green)).div(COLOUR_CEILING)).toInt(),
            ((foregroundColor.green.times(backgroundColor.green)).div(COLOUR_CEILING)).toInt())
}

object Screen : BlendMode {
    override fun combine(foregroundColor: Color, backgroundColor: Color): Color =
        Color(
            (COLOUR_CEILING
                .minus(
                    (COLOUR_CEILING.minus(foregroundColor.red))
                        .times(COLOUR_CEILING.minus(backgroundColor.red))
                        .div(COLOUR_CEILING)
                )).toInt(),
            (COLOUR_CEILING
                .minus(
                    (COLOUR_CEILING.minus(foregroundColor.green))
                        .times(COLOUR_CEILING.minus(backgroundColor.green))
                        .div(COLOUR_CEILING)
                )).toInt(),
            (COLOUR_CEILING
                .minus(
                    (COLOUR_CEILING.minus(foregroundColor.blue))
                        .times(COLOUR_CEILING.minus(backgroundColor.blue))
                        .div(COLOUR_CEILING)
                )).toInt()
        )
}

object NoBlend: BlendMode {
    override fun combine(foregroundColor: Color, backgroundColor: Color): Color = foregroundColor
}