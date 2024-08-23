package com.marwa.androidproject.base

import com.google.gson.annotations.SerializedName

class BaseException(
    @SerializedName("status_code") val statusCode: Int,
    @SerializedName("status_message") val statusMessage: String,
    @SerializedName("success") val success: Boolean
)