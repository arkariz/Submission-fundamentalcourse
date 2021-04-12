package com.arrkariz.submission1fundamentalcourse.view.detail

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.arrkariz.submission1fundamentalcourse.api.RetrofitClient
import com.arrkariz.submission1fundamentalcourse.data.local.FavoriteDatabase
import com.arrkariz.submission1fundamentalcourse.data.local.FavoriteUser
import com.arrkariz.submission1fundamentalcourse.data.local.FavoriteUserDao
import com.arrkariz.submission1fundamentalcourse.model.DetailUserResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailUserViewModel(application: Application) : AndroidViewModel(application) {
    val user = MutableLiveData<DetailUserResponse>()

    private var userDao: FavoriteUserDao?
    private var favDB: FavoriteDatabase?

    init{
        favDB = FavoriteDatabase.getDatabase(application)
        userDao = favDB?.favoriteUserDao()
    }

    fun setUserDetail(username: String) {
        RetrofitClient.apiInstance
            .getUserDetail(username)
            .enqueue(object : Callback<DetailUserResponse> {
                override fun onResponse(
                    call: Call<DetailUserResponse>,
                    response: Response<DetailUserResponse>
                ) {
                    if (response.isSuccessful) {
                        user.postValue(response.body())
                    }
                }

                override fun onFailure(call: Call<DetailUserResponse>, t: Throwable) {
                    t.message?.let { Log.d("Failur", it) }
                }

            })
    }

    fun getUserDetail(): LiveData<DetailUserResponse> {
        return user
    }

    fun addToFavorite(username: String, id: Int, avatar_url: String){
       CoroutineScope(Dispatchers.IO).launch {
           var user = FavoriteUser(
               username,
               id,
               avatar_url
           )
           userDao?.addToFavorite(user)
       }
    }

    suspend fun checkUser(id: Int) = userDao?.checkUser(id)

    fun removeFromFavorite(id: Int){
        CoroutineScope(Dispatchers.IO).launch {
            userDao?.removeFromFavorite(id)
        }
    }
}
