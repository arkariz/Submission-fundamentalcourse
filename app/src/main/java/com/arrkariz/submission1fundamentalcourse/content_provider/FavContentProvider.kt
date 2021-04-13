package com.arrkariz.submission1fundamentalcourse.content_provider

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import com.arrkariz.submission1fundamentalcourse.data.local.FavoriteDatabase
import com.arrkariz.submission1fundamentalcourse.data.local.FavoriteUserDao

class FavContentProvider : ContentProvider() {
    private lateinit var favDao: FavoriteUserDao

    companion object{
        const val AUTHORITY = "com.arrkariz.submission1fundamentalcourse"
        const val ID_FAV_USER = 1
        const val TABLE_NAME = "favorite_user"
        val uriMatcher = UriMatcher(UriMatcher.NO_MATCH)

        init {
            uriMatcher.addURI(AUTHORITY, TABLE_NAME, ID_FAV_USER)
        }
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int {
        TODO("Implement this to handle requests to delete one or more rows")
    }

    override fun getType(uri: Uri): String? {
        TODO(
            "Implement this to handle requests for the MIME type of the data" +
                    "at the given URI"
        )
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        TODO("Implement this to handle requests to insert a new row.")
    }

    override fun onCreate(): Boolean {
        favDao = context?.let {
            FavoriteDatabase.getDatabase(it)?.favoriteUserDao()
        }!!
        return false
    }

    override fun query(
        uri: Uri, projection: Array<String>?, selection: String?,
        selectionArgs: Array<String>?, sortOrder: String?
    ): Cursor? {
        var cursor: Cursor?
        when(uriMatcher.match(uri)){
            ID_FAV_USER -> {
                cursor = favDao.getAll()
                if (context != null){
                    cursor.setNotificationUri(context?.contentResolver, uri)
                }
            } else -> {
                cursor = null
            }
        }
        return cursor
    }

    override fun update(
        uri: Uri, values: ContentValues?, selection: String?,
        selectionArgs: Array<String>?
    ): Int {
        TODO("Implement this to handle requests to update one or more rows.")
    }
}