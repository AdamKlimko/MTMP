package com.example.mtmp_cv2

import android.util.Log
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
                override fun onResponse( call: Call<CalculationData>, response: Response<CalculationData>) {
                    val calculationData = response.body()
                    onResult(calculationData)
                }
            }
        )
    }
}