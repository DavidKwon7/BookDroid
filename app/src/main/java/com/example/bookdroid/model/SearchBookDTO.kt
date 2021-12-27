package com.example.bookdroid.model

import com.google.gson.annotations.SerializedName

//DTO란 계층간 데이터 교환을 위해 사용하는 객체

data class SearchBookDTO(
    @SerializedName("title") val title : String,
    @SerializedName("item") val books : List<Book>
)
