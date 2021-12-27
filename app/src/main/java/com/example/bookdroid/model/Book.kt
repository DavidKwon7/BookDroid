package com.example.bookdroid.model

import com.google.gson.annotations.SerializedName

//데이터를 저장하는 곳. 서버에서 오는 정보 중 어떤 정보, 어떤 type으로 가져올지

data class Book(
    //서버에서 받아온 아이디와 내가 만든 아이디가 다른 경우 매칭하기 위해서 @SerializedName 사용
    @SerializedName("itemId") val id:Long,
    @SerializedName("title") val title: String,
    @SerializedName("description") val description : String,
    @SerializedName("coverSmallUrl") val coverSmallUrl : String
)
