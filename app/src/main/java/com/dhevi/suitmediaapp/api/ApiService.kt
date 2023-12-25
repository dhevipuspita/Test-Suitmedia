package com.dhevi.suitmediaapp.api

import com.dhevi.suitmediaapp.data.UserResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("users")
    fun getUsers(
        @Query("page") page: Int,
        @Query("per_page") per_page: Int
    ): Call<UserResponse>
}