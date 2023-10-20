package com.example.infinitylist.data.retrofit


import com.example.infinitylist.data.model.Post
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface PostApi {
    @GET("/r/aww/hot.json")
    fun getPosts(
        @Query("limit") limit:Int,
        @Query("after") after:String
    ): Call<Post>

    companion object {
        private var api: PostApi? = null
        fun getInstance(): PostApi {
            if (api == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://www.reddit.com")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                api = retrofit.create(PostApi::class.java)
            }
            return api!!
        }
    }
}