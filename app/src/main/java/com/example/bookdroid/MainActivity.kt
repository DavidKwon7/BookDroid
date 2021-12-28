package com.example.bookdroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.MotionEvent
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bookdroid.adapter.BookAdapter
import com.example.bookdroid.api.BookService
import com.example.bookdroid.databinding.ActivityMainBinding
import com.example.bookdroid.model.BestSellerDTO
import com.example.bookdroid.model.SearchBookDTO
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    //지역 변수를 전역 변수로 바꿔주기
    private lateinit var adapter: BookAdapter
    private lateinit var bookService: BookService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //뷰바인딩 (가져올 xml파일을 대문자로 써줌)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initBookRecyclerView()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://book.interpark.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        bookService = retrofit.create(BookService::class.java)

        bookService.getBestSellerBooks(getString(R.string.interparkAPIKey))
            .enqueue(object : Callback<BestSellerDTO>{
                override fun onResponse(
                    call: Call<BestSellerDTO>,
                    response: Response<BestSellerDTO>
                ) {
                    if (response.isSuccessful.not()){
                        return
                    }
                    response.body()?.let {
                        Log.d(TAG, it.toString())

                        it.books.forEach{book ->
                            Log.d(TAG, book.toString())
                        }

                        //change 된다.
                        adapter.submitList(it.books)
                    }
                }

                override fun onFailure(call: Call<BestSellerDTO>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })

        binding.searchEditText.setOnKeyListener { v, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == MotionEvent.ACTION_DOWN){
                search(binding.searchEditText.text.toString())
                return@setOnKeyListener true
            }
            //이벤트가 처리가 안 되었음.
            else return@setOnKeyListener true
        }
    }

    private fun search(keyword:String){
        bookService.getBooksByName(getString(R.string.interparkAPIKey),keyword)
            .enqueue(object : Callback<SearchBookDTO>{
                override fun onResponse(
                    call: Call<SearchBookDTO>,
                    response: Response<SearchBookDTO>
                ) {
                    if (response.isSuccessful.not()){
                        return
                    }
                    response.body()?.let {
                        Log.d(TAG, it.toString())

                        it.books.forEach{book ->
                            Log.d(TAG, book.toString())
                        }

                        //change 된다.
                        adapter.submitList(it.books)
                    }
                }

                override fun onFailure(call: Call<SearchBookDTO>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })
    }

    fun initBookRecyclerView(){
        adapter = BookAdapter()

        binding.bookRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.bookRecyclerView.adapter = adapter
    }

    //계속 쓰일거면 companion object로 만들어둠
    companion object{
        private const val TAG = "MainActivity"
    }
}