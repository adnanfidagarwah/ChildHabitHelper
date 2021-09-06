package com.example.dummyproject.base

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class BaseResponse<Any>(
    @SerializedName("statusCode") val statusCode: Int,
    @SerializedName("status") val status: Boolean,
    @SerializedName("message") val message: String,
    @SerializedName("data") val data: Any
)