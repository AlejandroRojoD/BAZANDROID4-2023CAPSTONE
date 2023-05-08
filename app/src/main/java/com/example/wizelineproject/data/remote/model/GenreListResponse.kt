package com.example.wizelineproject.data.remote.model

import com.example.wizelineproject.data.model.BaseError

class GenreListResponse : BaseError() {
    val genres: List<GenreResponse> = listOf()
}