package com.larapin.kotlinsub2.event

import com.google.gson.Gson
import com.larapin.kotlinsub2.api.ApiRepository
import com.larapin.kotlinsub2.api.TheSportDBApi
import com.larapin.kotlinsub2.model.EventResponse
import com.larapin.kotlinsub2.model.EventSearchResponse
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class EventPresenter(private val view: EventView,
                     private val apiRepository: ApiRepository,
                     private val gson: Gson){
    fun getEventList(league: String?, event: String?){
        view.showLoading()
        doAsync {
            val data = gson.fromJson(apiRepository
                    .doRequest(TheSportDBApi.getEvent(league, event)),
                    EventResponse::class.java
            )

            uiThread {
                view.hideLoading()
                try {
                    view.showEventList(data.events)
                } catch (e: Exception) {
                    view.showEmptyData()
                }
            }
        }
    }

    fun getEventSearch(name: String?){
        view.showLoading()
        doAsync {
            val data = gson.fromJson(apiRepository
                    .doRequest(TheSportDBApi.getEventbySearch(name)),
                    EventSearchResponse::class.java
            )

            uiThread {
                view.hideLoading()
                try {
                    view.showEventList(data.event)
                } catch (e: Exception) {
                    view.showEmptyData()
                }
            }
        }
    }
}
