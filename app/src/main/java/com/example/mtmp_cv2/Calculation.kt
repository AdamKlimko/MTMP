package com.example.mtmp_cv2

class Calculation(
    var angle: Double,
    var velocity: Double
) {
    private val g: Double = 9.80665
    val xArray = arrayListOf<Float>()
    val yArray = arrayListOf<Float>()
    val timeArray = arrayListOf<Float>()

    fun calculate(): CalculationData {


        var x = 0.0; var y = 0.0; var time = 0.0
        val timeStop = (2 * velocity * kotlin.math.sin(Math.toRadians(angle))) / g

        while (time < timeStop) {
            x = calculateX(velocity, time, angle)
            y = calculateY(velocity, time, angle)
            fillArrays(x, y, time)
            time += 0.1
        }

        time -= 0.1
        time = - ((0.0 - velocity * kotlin.math.sin(Math.toRadians(angle))) / (g/2))

        x = calculateX(velocity, time, angle)
        fillArrays(x, 0.0, time)  // Insert last point where object hits ground

        return CalculationData(xArray, yArray, timeArray)
    }

    private fun fillArrays(x: Double, y: Double, t: Double) {
        xArray.add(x.toFloat())
        yArray.add(y.toFloat())
        timeArray.add(t.toFloat())
    }

    private fun calculateX(v: Double, t: Double, a: Double): Double {
        return v * t * kotlin.math.cos(Math.toRadians(a))
    }

    private fun calculateY(v: Double, t: Double, a: Double): Double {
        return v * t * kotlin.math.sin(Math.toRadians(a)) - (0.5 * g * t * t)
    }
}