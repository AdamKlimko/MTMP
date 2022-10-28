package com.example.mtmp_cv2.service

import android.util.Log
import com.example.mtmp_cv2.model.CalculationData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CalculationService {
    fun fetchCalculation(calculationRequest: CalculationRequest, onResult: (CalculationData?) -> Unit){
        val retrofit = ServiceBuilder.buildService(ICalculationService::class.java)
        retrofit.fetchCalculation(calculationRequest).enqueue(
            object : Callback<CalculationData> {
                override fun onFailure(call: Call<CalculationData>, t: Throwable) {
                    onResult(null)
                    t.printStackTrace()
                    Log.e("error", t.message.toString())

                }
                override fun onResponse(call: Call<CalculationData>, response: Response<CalculationData>) {
                    Log.i("info", response.toString())
                    val calculationData = response.body()
                    onResult(calculationData)
                }
            }
        )
    }
}