package com.example.mtmp_cv2

import com.github.mikephil.charting.data.Entry

class Calculation(
    var angle: Double,
    var velocity: Double
) {
    private val g: Double = 9.80665

    fun calculate(): CalculationData {
        val xArray = arrayListOf<Float>()
        val yArray = arrayListOf<Float>()
        val timeArray = arrayListOf<Float>()

        var x = 0.0; var y = 0.0; var time = 0.0
        val timeStop = (2 * velocity * kotlin.math.sin(Math.toRadians(angle))) / g

        while (time < timeStop) {
            if (y < 0) {
                break
            }

            x = calculateX(x, velocity, time, angle)
            y = calculateY(y, velocity, time, angle)
            xArray.add(x.toFloat())
            yArray.add(y.toFloat())
            timeArray.add(time.toFloat())
            time += 0.1
        }

        return CalculationData(xArray, yArray, timeArray)
    }

    private fun calculateX(x: Double, v: Double, t: Double, a: Double): Double {
        return (v * t * kotlin.math.cos(Math.toRadians(a)))
    }

    private fun calculateY(y: Double, v: Double, t: Double, a: Double): Double {
        return (v * t * kotlin.math.sin(Math.toRadians(a))) - (0.5 * g * t * t)
    }
}