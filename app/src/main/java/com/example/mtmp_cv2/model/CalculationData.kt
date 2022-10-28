package com.example.mtmp_cv2.model

import com.google.gson.annotations.SerializedName

class CalculationData(
    @SerializedName("xarray")
    var xArray: ArrayList<Float>,
    @SerializedName("yarray")
    var yArray: ArrayList<Float>,
    @SerializedName("tarray")
    var tArray: ArrayList<Float>
) { }