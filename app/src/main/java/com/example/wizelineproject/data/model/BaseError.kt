package com.example.wizelineproject.data.model

data class BaseError(
    var status_code: Int = 0,
    var status_message: String = "",
    var success: Boolean = false
)