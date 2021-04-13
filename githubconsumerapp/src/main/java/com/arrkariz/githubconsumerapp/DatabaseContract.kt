package com.arrkariz.githubconsumerapp

import android.net.Uri
import android.provider.BaseColumns

object DatabaseContract {
    const val AUTHORITY = "com.arrkariz.submission1fundamentalcourse"
    const val SCHEME = "content"

    internal class FavoriteColumn: BaseColumns{
        companion object{
            const val TABLE_NAME = "favorite_user"
            const val ID = "id"
            const val USERNAME = "login"
            const val AVATAR = "avatar_url"

            val CONTENT_URI = Uri.Builder().scheme(SCHEME)
                .authority(AUTHORITY)
                .appendPath(TABLE_NAME)
                .build()
        }
    }
}