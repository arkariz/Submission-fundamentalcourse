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
    @Headers("Authorization: token 7e76fc4f93fe8de29e777f9ce7b2498c88dec7f3")
    fun getSearchUsers(
        @Query("q") query: String
    ): retrofit2.Call<UserResponse>

    @GET("users/{username}")
    @Headers("Authorization: token 7e76fc4f93fe8de29e777f9ce7b2498c88dec7f3")
    fun getUserDetail(
            @Path("username") username: String
    ): Call<DetailUserResponse>

    @GET("users/{username}/followers")
    @Headers("Authorization: token 7e76fc4f93fe8de29e777f9ce7b2498c88dec7f3")
    fun getFollowers(
            @Path("username") username: String
    ): Call<ArrayList<Userdata>>

    @GET("users/{username}/following")
    @Headers("Authorization: token 7e76fc4f93fe8de29e777f9ce7b2498c88dec7f3")
    fun getFollowing(
            @Path("username") username: String
    ): Call<ArrayList<Userdata>>

}