package com.larapin.kotlinsub2.detail.team

import com.google.gson.Gson
import com.larapin.kotlinsub2.api.ApiRepository
import com.larapin.kotlinsub2.api.TheSportDBApi
import com.larapin.kotlinsub2.model.TeamsResponse
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class TeamDetailPresenter(private val view: TeamDetailView,
                           private val apiRepository: ApiRepository,
                           private val gson: Gson){

    fun getTeamDetail(eventId: String?){
        view.showLoading()
        doAsync {
            val data = gson.fromJson(apiRepository
                    .doRequest(TheSportDBApi.getDetailTeam(eventId)),
                    TeamsResponse::class.java
            )

            uiThread {
                view.hideLoading()
                view.showTeamDetail(data.teams)
            }
        }
    }
}