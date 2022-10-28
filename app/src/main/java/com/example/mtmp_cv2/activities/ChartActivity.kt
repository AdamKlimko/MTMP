package com.example.mtmp_cv2.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mtmp_cv2.R
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.*

class ChartActivity : AppCompatActivity() {
    lateinit var lineList: ArrayList<Entry>
    lateinit var lineDataSet: LineDataSet
    lateinit var lineData: LineData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chart)

        val yArray = intent.getSerializableExtra("yArray") as ArrayList<Float>
        val tArray = intent.getSerializableExtra("tArray") as ArrayList<Float>
        drawChart(tArray, yArray)
    }

    private fun drawChart(xArray: ArrayList<Float>, yArray: ArrayList<Float>) {
        lineList = ArrayList()

        for (i in 0 until xArray.size) {
            lineList.add(Entry(xArray[i], yArray[i]))
        }

        lineDataSet = LineDataSet(lineList, "Flying object")
        lineData = LineData(lineDataSet)
        val lineChart = findViewById<LineChart>(R.id.line_chart)
        lineChart.data = lineData
    }
}