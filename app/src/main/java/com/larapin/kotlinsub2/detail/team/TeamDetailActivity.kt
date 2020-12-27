package com.larapin.kotlinsub2.detail.team

import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.widget.ImageView
import android.widget.ProgressBar
import com.google.gson.Gson
import com.larapin.kotlinsub2.R
import com.larapin.kotlinsub2.api.ApiRepository
import com.larapin.kotlinsub2.model.Teams
import com.larapin.kotlinsub2.util.invisible
import com.larapin.kotlinsub2.util.visible
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_teams_detail.*
import okhttp3.OkHttpClient
import org.jetbrains.anko.find
import org.jetbrains.anko.support.v4.onRefresh


class TeamDetailActivity : AppCompatActivity(), TeamDetailView {
    private lateinit var presenter: TeamDetailPresenter
    private lateinit var idEvent: String
    private var idHome: String = ""
    private var idAway: String = ""
    private lateinit var progressBar: ProgressBar
    private lateinit var swipeRefresh: SwipeRefreshLayout
    val client = OkHttpClient()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_teams_detail)
        supportActionBar?.title = "Team Detail"

        progressBar = find(R.id.progress_bar_detail)
        swipeRefresh = find(R.id.swipe_refresh_detail)

        val intent = intent
        idEvent = intent.getStringExtra("id")

        val request = ApiRepository()
        val gson = Gson()
        presenter = TeamDetailPresenter(this, request, gson)
        presenter.getTeamDetail(idEvent)
        swipeRefresh.onRefresh {
            presenter.getTeamDetail(idEvent)
        }
    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showTeamDetail(data: List<Teams>) {
        swipeRefresh.isRefreshing = false
        tv_teamname.text = data[0].strTeam
        tv_alternateteam.text = data[0].strAlternate
        tv_teamyear.text = data[0].intFormedYear
        tv_teamdetails.text = data[0].strDescriptionEN
        tv_teamstadiumdesk.text = data[0].strStadiumDescription
        val teamBanner: ImageView = findViewById(R.id.img_teambanner)
        val teamBadge: ImageView = findViewById(R.id.img_teambadge)
        val teamJersey: ImageView = findViewById(R.id.img_teamjersey)
        val teamStadium: ImageView = findViewById(R.id.img_teamstadium)
        Picasso.get()
                .load(data[0].strTeamBanner)
                .into(teamBanner)
        Picasso.get()
                .load(data[0].strTeamBadge)
                .into(teamBadge)
        Picasso.get()
                .load(data[0].strTeamJersey)
                .into(teamJersey)
        Picasso.get()
                .load(data[0].strStadiumThumb)
                .placeholder(R.drawable.noimage)
                .error(R.drawable.noimage)
                .into(teamStadium)

    }
}
