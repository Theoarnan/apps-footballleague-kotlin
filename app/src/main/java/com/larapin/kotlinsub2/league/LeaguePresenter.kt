package com.larapin.kotlinsub2.league

import com.google.gson.Gson
import com.larapin.kotlinsub2.api.ApiRepository
import com.larapin.kotlinsub2.api.TheSportDBApi
import com.larapin.kotlinsub2.model.LeagueResponse
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class LeaguePresenter(private val view: LeagueView,
                     private val apiRepository: ApiRepository,
                     private val gson: Gson) {
    fun getLeagueList() {
        view.showLoading()
        doAsync {
            val data = gson.fromJson(apiRepository
                    .doRequest(TheSportDBApi.getLeague()),
                    LeagueResponse::class.java
            )

            uiThread {
                view.hideLoading()
                view.showLeagueList(data.countrys)
            }
        }
    }
}