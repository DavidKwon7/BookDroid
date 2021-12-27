package com.example.bookdroid.model

import com.google.gson.annotations.SerializedName

//전체 모델(data)에서 필요한 모델만 꺼내올 수 있게 연결시켜주는 기능

//내가 필요한 데이터는 전체 데이터가 아닌 전체 중 일부의 데이터이다.
data class BestSellerDTO (
    @SerializedName("title") val title : String,
    @SerializedName("item") val books : List<Book>
        )
