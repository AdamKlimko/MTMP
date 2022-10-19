package com.example.mtmp_cv2

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface ICalculationService {

    @Headers("Content-Type: application/json")
    @POST("/calculation/")
    fun fetchCalculation(@Body calculationRequest: CalculationRequest): Call<CalculationData>
}