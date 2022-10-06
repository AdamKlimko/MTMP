package com.example.mtmp_cv2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.SeekBar
import android.widget.TextView
import kotlin.math.sqrt

class MainActivity : AppCompatActivity() {
    var angle: Double = 0.0
    var velocity: Double = 0.0
    val g: Double = 9.80665

    val xArray = arrayListOf<Double>()
    val yArray = arrayListOf<Double>()
    val timeArray = arrayListOf<Double>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textView1= findViewById<TextView>(R.id.textView)
        val textView2= findViewById<TextView>(R.id.textView2)

        findViewById<SeekBar>(R.id.seekBar).setOnSeekBarChangeListener(
            object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seek: SeekBar,progress: Int, fromUser: Boolean) {
                    textView1.text = progress.toString()
                    angle = progress.toDouble()
                }
                override fun onStartTrackingTouch(p0: SeekBar?) { }
                override fun onStopTrackingTouch(p0: SeekBar?) { }
            }
        )

        findViewById<SeekBar>(R.id.seekBar2).setOnSeekBarChangeListener(
            object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(seek: SeekBar,progress: Int, fromUser: Boolean) {
                    textView2.text = progress.toString()
                    velocity = progress.toDouble()
                }
                override fun onStartTrackingTouch(p0: SeekBar?) { }
                override fun onStopTrackingTouch(p0: SeekBar?) { }
            }
        )

        findViewById<Button>(R.id.calculateBtn).setOnClickListener {
            calculate()
        }

        findViewById<Button>(R.id.listButton).setOnClickListener {
            listResults()
        }
    }

    private fun calculate() {
        var x = 0.0
        var y = 0.0
        var time = 0.0

        while (true) {
            if (y < 0) {
                break
            }

            xArray.add(x)
            yArray.add(y)
            timeArray.add(time)
            x = calculateX(x, velocity, time, angle)
            y = calculateY(y, velocity, time, angle)
            time += 0.1
        }
    }

    private fun calculateX(x: Double, v: Double, t: Double, a: Double): Double {
        return x + (v * t * kotlin.math.cos(Math.toRadians(a)))
    }

    private fun calculateY(y: Double, v: Double, t: Double, a: Double): Double {
        return y + (v * t * kotlin.math.sin(Math.toRadians(a))) - (0.5 * g * t * t)
    }

    private fun listResults() {
        val intent = Intent(this, ListActivity::class.java)
        intent.putExtra("xArray", this.xArray)
        intent.putExtra("yArray", this.yArray)
        intent.putExtra("timeArray", this.timeArray)
        startActivity(intent)
    }
}