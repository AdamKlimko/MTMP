package com.example.mtmp_cv2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast
import com.google.gson.Gson

class MainActivity : AppCompatActivity() {
    var angle: Double = 0.0
    var velocity: Double = 0.0

    lateinit var calculationData: CalculationData
    var isResult = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val angleValTextView= findViewById<TextView>(R.id.angleValTextView)
        val velocityValTextView= findViewById<TextView>(R.id.velocityValTextView)

        findViewById<SeekBar>(R.id.seekBar).setOnSeekBarChangeListener(
            object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seek: SeekBar,progress: Int, fromUser: Boolean) {
                    angleValTextView.text = "$progress°"
                    angle = progress.toDouble()
                }
                override fun onStartTrackingTouch(p0: SeekBar?) { }
                override fun onStopTrackingTouch(p0: SeekBar?) { }
            }
        )

        findViewById<SeekBar>(R.id.seekBar2).setOnSeekBarChangeListener(
            object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(seek: SeekBar,progress: Int, fromUser: Boolean) {
                    velocityValTextView.text = "${progress}m/s"
                    velocity = progress.toDouble()
                }
                override fun onStartTrackingTouch(p0: SeekBar?) { }
                override fun onStopTrackingTouch(p0: SeekBar?) { }
            }
        )

        findViewById<Button>(R.id.calculateBtn).setOnClickListener { calculateLocal() }
        findViewById<Button>(R.id.calculateServerBtn).setOnClickListener { calculateServer() }
        findViewById<Button>(R.id.listButton).setOnClickListener { listResults() }
        findViewById<Button>(R.id.graphButton).setOnClickListener { showChart() }
    }

    private fun listResults() {
        if(isResult) {
            val intent = Intent(this, ListActivity::class.java)
            intent.putExtra("xArray", this.calculationData.xArray)
            intent.putExtra("yArray", this.calculationData.yArray)
            intent.putExtra("tArray", this.calculationData.tArray)
            startActivity(intent)

        }
        else {
            Toast.makeText(applicationContext,"Throw not yet calculated!",Toast.LENGTH_SHORT).show()
        }
    }

    private fun showChart() {
        if(isResult) {
            val intent = Intent(this, ChartActivity::class.java)
            intent.putExtra("yArray", this.calculationData.yArray)
            intent.putExtra("tArray", this.calculationData.tArray)
            startActivity(intent)
        }
        else {
            Toast.makeText(applicationContext,"Throw not yet calculated!",Toast.LENGTH_SHORT).show()
        }
    }

    private fun calculateLocal() {
        calculationData = Calculation(angle, velocity).calculate()
        isResult = true
        Toast.makeText(applicationContext,"Throw calculated",Toast.LENGTH_SHORT).show()
    }

    private fun calculateServer() {

        val calculationService = CalculationService()
        val calculationRequest = CalculationRequest(angle, velocity)

        calculationService.fetchCalculation(calculationRequest) {
            if (it != null) {
                isResult = true
                Log.i("info", it.toString())
                calculationData = it
                Log.i("info", calculationData.toString())
                Toast.makeText(applicationContext,"Throw calculated",Toast.LENGTH_SHORT).show()

            } else {
                isResult = false
                Toast.makeText(applicationContext,"Request error",Toast.LENGTH_SHORT).show()
            }
        }
    }
}