package com.larapin.kotlinsub2.model

import com.google.gson.annotations.SerializedName

data class Event(
        @SerializedName("idEvent")
        var eventId: String? = null,

        @SerializedName("dateEvent")
        var eventDate: String? = null,

        @SerializedName("idHomeTeam")
        var idHome: String? = null,

        @SerializedName("strHomeTeam")
        var teamHome: String? = null,

        @SerializedName("intHomeScore")
        var scoreHome: String? = null,

        @SerializedName("idAwayTeam")
        var idAway: String? = null,

        @SerializedName("strAwayTeam")
        var teamAway: String? = null,

        @SerializedName("intAwayScore")
        var scoreAway: String? = null

)