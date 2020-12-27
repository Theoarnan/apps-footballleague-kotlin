package com.larapin.kotlinsub2.detail.league

import com.larapin.kotlinsub2.model.LeagueDetail
import com.larapin.kotlinsub2.model.Teams

interface LeagueDetailView {
    fun showLoading()
    fun hideLoading()
    fun showLeagueDetail(data: List<LeagueDetail>)
    fun showTeamsDetail(team: List<Teams>)
}