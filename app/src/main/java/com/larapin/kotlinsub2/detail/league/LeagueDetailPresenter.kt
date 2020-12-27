package com.larapin.kotlinsub2.detail.league

import com.google.gson.Gson
import com.larapin.kotlinsub2.api.ApiRepository
import com.larapin.kotlinsub2.api.TheSportDBApi
import com.larapin.kotlinsub2.model.LeagueDetailResponse
import com.larapin.kotlinsub2.model.TeamsResponse
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class LeagueDetailPresenter(private val view: LeagueDetailView,
                           private val apiRepository: ApiRepository,
                           private val gson: Gson){

    fun getDetailLeague(eventId: String?){
        view.showLoading()
        doAsync {
            val data = gson.fromJson(apiRepository
                    .doRequest(TheSportDBApi.getDetailLeague(eventId)),
                    LeagueDetailResponse::class.java
            )

            uiThread {
                view.hideLoading()
                view.showLeagueDetail(data.leagues)
            }
        }
    }

    fun getTeamsLeague(eventId: String?){
        view.showLoading()
        doAsync {
            val team = gson.fromJson(apiRepository
                    .doRequest(TheSportDBApi.getTeamAll(eventId)),
                    TeamsResponse::class.java
            )

            uiThread {
                view.hideLoading()
                view.showTeamsDetail(team.teams)
            }
        }
    }
}