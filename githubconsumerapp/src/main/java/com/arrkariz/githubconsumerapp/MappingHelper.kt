package com.arrkariz.githubconsumerapp

import android.database.Cursor
import com.arrkariz.submission1fundamentalcourse.Userdata

object MappingHelper {
    fun mapCursorToArrayList(cursor: Cursor?): ArrayList<Userdata>{
        val list = ArrayList<Userdata>()
        if (cursor != null){
            while (cursor.moveToNext()){
                val id = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseContract.FavoriteColumn.ID))
                val avatar = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.FavoriteColumn.AVATAR))
                val username = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.FavoriteColumn.USERNAME))

                list.add(
                    Userdata(
                        avatar,
                        username,
                        id
                    )
                )
            }
        }
        return list
    }
}