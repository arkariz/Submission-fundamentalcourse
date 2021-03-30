package com.arrkariz.submission1fundamentalcourse.api

import com.arrkariz.submission1fundamentalcourse.model.UserResponse
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface Api {
    @GET("search/users")
    @Headers("Authorization: token a55840eaa47d01cd43ae0906e5906e88ab24299c")
    fun getSearchUsers(
        @Query("q") query: String
    ): retrofit2.Call<UserResponse>
}