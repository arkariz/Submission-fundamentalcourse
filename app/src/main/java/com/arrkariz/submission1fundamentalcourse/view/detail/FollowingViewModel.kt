package com.arrkariz.submission1fundamentalcourse.view.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.arrkariz.submission1fundamentalcourse.Userdata
import com.arrkariz.submission1fundamentalcourse.api.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList
import kotlin.math.log

class FollowingViewModel: ViewModel() {
    val listFollowing = MutableLiveData<ArrayList<Userdata>>()

    fun setListFollowing(username: String){
        RetrofitClient.apiInstance
                .getFollowing(username)
                .enqueue(object : Callback<ArrayList<Userdata>> {
                    override fun onResponse(call: Call<ArrayList<Userdata>>, response: Response<ArrayList<Userdata>>) {
                        if (response.isSuccessful){
                            listFollowing.postValue(response.body())
                        }
                    }

                    override fun onFailure(call: Call<ArrayList<Userdata>>, t: Throwable) {
                        Log.d("Failure", t.message.toString())
                    }

                })
    }

    fun getListFollowing(): LiveData<ArrayList<Userdata>>{
        return listFollowing
    }
}