package com.larapin.kotlinsub2.model

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import com.google.gson.annotations.SerializedName

data class League(
        @SerializedName("idLeague")
        var idLeague: String? = null,

        @SerializedName("strLeague")
        var strLeague: String? = null,

        @SerializedName("strSport")
        var strSport: String? = null,

        @SerializedName("strBadge")
        var strBadge: String? = null,

        @SerializedName("strLeagueAlternate")
        var strLeagueAlternate: String? = null
)