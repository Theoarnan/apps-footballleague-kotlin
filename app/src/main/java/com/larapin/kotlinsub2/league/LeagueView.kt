package com.larapin.kotlinsub2.league

import com.larapin.kotlinsub2.model.League

interface LeagueView {
        fun showLoading()
        fun hideLoading()
        fun showLeagueList(data: List<League>)
}