package com.larapin.kotlinsub2.api

import com.larapin.kotlinsub2.BuildConfig

object TheSportDBApi {

    // Get All League
    fun getLeague(): String{
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}" + "/search_all_leagues.php?c=England&s=Soccer"
    }

    // Get Detail League
    fun getDetailLeague(event: String?): String{
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}" + "/lookupleague.php?id=" +event
    }

    // Get Team by League
    fun getTeamAll(event: String?): String {
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}" + "/search_all_teams.php?l=" +event
    }

    // Get Detail Team
    fun getDetailTeam(event: String?): String {
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}" + "/lookupteam.php?id=" +event
    }

    // Get Event Last Next
    fun getEvent(league: String?, event: String?): String{
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}" + "/"+event+".php?id="+league
    }

    // Get Detail Event
    fun getEventDetail(eventId: String?): String{
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}" + "/lookupevent.php?id="+eventId
    }

    // Search
    fun getEventbySearch(event: String?): String{
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}" + "/searchevents.php?e=" +event
    }


}