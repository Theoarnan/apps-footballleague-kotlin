package com.larapin.kotlinsub2.model

import com.google.gson.annotations.SerializedName

class LeagueDetail {
    @SerializedName("idLeague")
    var idLeague: String? = null

    @SerializedName("strLeague")
    var strLeague: String? = null

    @SerializedName("strLeagueAlternate")
    var strLeagueAlternate: String? = null

    @SerializedName("strSport")
    var strSport: String? = null

    @SerializedName("intFormedYear")
    var intFormedYear: String? = null

    @SerializedName("strWebsite")
    var strWebsite: String? = null

    @SerializedName("strFacebook")
    var strFacebook: String? = null

    @SerializedName("strTwitter")
    var strTwitter: String? = null

    @SerializedName("strYoutube")
    var strYoutube: String? = null

    @SerializedName("strDescriptionEN")
    var strDescriptionEN: String? = null

    @SerializedName("strBanner")
    var strBanner: String? = null

    @SerializedName("strBadge")
    var strBadge: String? = null
}