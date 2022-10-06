package com.example.mtmp_cv2

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity


class ListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
        val xArray = intent.getSerializableExtra("xArray") as ArrayList<*>
        val yArray = intent.getSerializableExtra("yArray") as ArrayList<*>
        val timeArray = intent.getSerializableExtra("timeArray") as ArrayList<*>

        val listView = findViewById<ListView>(R.id.listView)

        val listItems = arrayOfNulls<String>(xArray.size)

        for (i in 0 until xArray.size) {
            val row = createRowString(xArray[i], yArray[i], timeArray[i])
            listItems[i] = row
        }

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, listItems)
        listView.adapter = adapter

    }

    private fun createRowString(x: Any, y: Any, t: Any): String {
        return "x: ${String.format("%.2f", x)}, " +
                "y: ${String.format("%.2f", y)}, " +
                "t: ${String.format("%.2f", t)}"
    }
}