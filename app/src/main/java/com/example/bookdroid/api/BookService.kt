package com.example.bookdroid.api

import com.example.bookdroid.model.BestSellerDTO
import com.example.bookdroid.model.SearchBookDTO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

//interface는 정의를 내린 것 일뿐, 구현된 것이 아니다. 그렇기에 추가적으로 구현이 필요하다!(retrofit.create를 통해)
interface BookService {
    @GET("api/search.api?output=json")
    fun getBooksByName(
        @Query("key") apiKey : String,
        @Query("query") keyword : String
    ) : Call<SearchBookDTO>

    @GET("/api/bestSeller.api?output=json&categoryId=100")
    fun getBestSellerBooks(
        @Query("key") apiKey: String
    ) : Call<BestSellerDTO>

}