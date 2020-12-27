package com.larapin.kotlinsub2.detail.team

import com.larapin.kotlinsub2.model.Teams

interface TeamDetailView{
    fun showLoading()
    fun hideLoading()
    fun showTeamDetail(data: List<Teams>)
}