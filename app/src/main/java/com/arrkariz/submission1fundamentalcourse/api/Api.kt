package com.arrkariz.submission1fundamentalcourse.api

import com.arrkariz.submission1fundamentalcourse.Userdata
import com.arrkariz.submission1fundamentalcourse.model.DetailUserResponse
import com.arrkariz.submission1fundamentalcourse.model.UserResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {
    @GET("search/users")
    @Headers("Authorization: token 621756eba91dc9e746d731c9a573614f2fbf4df7")
    fun getSearchUsers(
        @Query("q") query: String
    ): retrofit2.Call<UserResponse>

    @GET("users/{username}")
    @Headers("Authorization: token 621756eba91dc9e746d731c9a573614f2fbf4df7")
    fun getUserDetail(
            @Path("username") username: String
    ): Call<DetailUserResponse>

    @GET("user/{username}/followers")
    @Headers("Authorization: token 621756eba91dc9e746d731c9a573614f2fbf4df7")
    fun getFollowers(
            @Path("username") username: String
    ): Call<ArrayList<Userdata>>

    @GET("user/{username}/following")
    @Headers("Authorization: token 621756eba91dc9e746d731c9a573614f2fbf4df7")
    fun getFollowing(
            @Path("username") username: String
    ): Call<ArrayList<Userdata>>

}