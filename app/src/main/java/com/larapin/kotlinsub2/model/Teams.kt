package com.larapin.kotlinsub2.model

import com.google.gson.annotations.SerializedName

data class Teams(
        @SerializedName("idTeam")
        var idTeam: String? = null,

        @SerializedName("strTeam")
        var strTeam: String? = null,

        @SerializedName("strAlternate")
        var strAlternate: String? = null,

        @SerializedName("intFormedYear")
        var intFormedYear: String? = null,

        @SerializedName("strStadium")
        var strStadium: String? = null,

        @SerializedName("strTeamBanner")
        var strTeamBanner: String? = null,

        @SerializedName("strTeamBadge")
        var strTeamBadge: String? = null,

        @SerializedName("strStadiumThumb")
        var strStadiumThumb: String? = null,

        @SerializedName("strDescriptionEN")
        var strDescriptionEN: String? = null,

        @SerializedName("strStadiumDescription")
        var strStadiumDescription: String? = null,

        @SerializedName("strTeamJersey")
        var strTeamJersey: String? = null,

        @SerializedName("strYoutube")
        var strYoutube: String? = null
)