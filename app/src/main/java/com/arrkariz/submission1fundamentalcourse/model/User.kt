package com.arrkariz.submission1fundamentalcourse

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


data class Userdata(
    val idDb: Int,
    val id: Int,
    val login: String,
    val avatar_url: String
)