package com.arrkariz.githubconsumerapp

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.arrkariz.submission1fundamentalcourse.Userdata

class FavoriteViewModel(application: Application) : AndroidViewModel(application) {
    private var list = MutableLiveData<ArrayList<Userdata>>()

    fun setFavUser(context: Context) {
        val cursor = context.contentResolver.query(
            DatabaseContract.FavoriteColumn.CONTENT_URI,
            null,
            null,
            null,
            null,
        )
        val listFormated = MappingHelper.mapCursorToArrayList(cursor)
        list.postValue(listFormated)
    }

    fun getFavoriteUser(): LiveData<ArrayList<Userdata>> {
        return list
    }
}