package com.arrkariz.submission1fundamentalcourse

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Userdata(
    val avatar_url: String?,
    val login: String?,
): Parcelable