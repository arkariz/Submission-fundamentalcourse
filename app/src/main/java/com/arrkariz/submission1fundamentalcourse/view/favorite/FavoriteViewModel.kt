package com.arrkariz.submission1fundamentalcourse.view.favorite

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.arrkariz.submission1fundamentalcourse.data.local.FavoriteDatabase
import com.arrkariz.submission1fundamentalcourse.data.local.FavoriteUser
import com.arrkariz.submission1fundamentalcourse.data.local.FavoriteUserDao

class FavoriteViewModel(application: Application): AndroidViewModel(application){
    private var userDao: FavoriteUserDao?
    private var favDB: FavoriteDatabase?

    init{
        favDB = FavoriteDatabase.getDatabase(application)
        userDao = favDB?.favoriteUserDao()
    }

    fun getFavoriteUser(): LiveData<List<FavoriteUser>>?{
        return userDao?.getFavoriteUser()
    }
}