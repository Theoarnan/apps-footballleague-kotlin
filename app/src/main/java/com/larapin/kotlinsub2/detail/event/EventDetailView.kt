package com.larapin.kotlinsub2.detail.event

import com.larapin.kotlinsub2.model.EventDetail

interface EventDetailView{
    fun showLoading()
    fun hideLoading()
    fun showEventDetail(data: List<EventDetail>)
}