package utils

fun maping(
    x: Float, inMin: Float, inMax: Float, outMin: Float, outMax: Float
): Float = (x - inMin) * (outMax - outMin) / (inMax - inMin) + outMin

fun maping(
    x: Int, inMin: Int, inMax: Int, outMin: Int, outMax: Int
): Int = (x - inMin) * (outMax - outMin) / (inMax - inMin) + outMin