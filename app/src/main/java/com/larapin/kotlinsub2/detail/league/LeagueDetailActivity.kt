package com.larapin.kotlinsub2.detail.league

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.ImageView
import android.widget.ProgressBar
import com.google.gson.Gson
import com.larapin.kotlinsub2.R
import com.larapin.kotlinsub2.api.ApiRepository
import com.larapin.kotlinsub2.detail.team.TeamDetailActivity
import com.larapin.kotlinsub2.event.EventFragment
import com.larapin.kotlinsub2.model.LeagueDetail
import com.larapin.kotlinsub2.model.Teams
import com.larapin.kotlinsub2.util.invisible
import com.larapin.kotlinsub2.util.visible
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_league_detail.*
import okhttp3.*
import org.jetbrains.anko.ctx
import org.jetbrains.anko.find
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.support.v4.onRefresh
import java.util.*

class LeagueDetailActivity : AppCompatActivity(), LeagueDetailView {
    private lateinit var presenter: LeagueDetailPresenter
    private lateinit var adapter: LeagueDetailAdapter
    private var events: MutableList<Teams> = mutableListOf()
    private lateinit var idEvent: String
    private lateinit var nameLeague: String
    private lateinit var listEvent: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var progressBars: ProgressBar
    private lateinit var swipeRefresh: SwipeRefreshLayout
    var event: String? = ""
    val client = OkHttpClient()

        private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_previous -> {
                val fragment = EventFragment.newInstance("eventspastleague", idEvent)
                addFragment(fragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_next -> {
                val fragment = EventFragment.newInstance("eventsnextleague",idEvent)
                addFragment(fragment)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_league_detail)
        supportActionBar?.title = "League Detail"
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        progressBar = find(R.id.progress_bar)
        progressBars = find(R.id.progress_bar_detail)
        swipeRefresh = find(R.id.swipe_refresh_detail)

        val intent = intent
        idEvent = intent.getStringExtra("id")
        nameLeague = intent.getStringExtra("name")

        val request = ApiRepository()
        val gson = Gson()
        presenter = LeagueDetailPresenter(this, request, gson)
        presenter.getDetailLeague(idEvent)
        swipeRefresh.onRefresh {
            presenter.getDetailLeague(idEvent)
        }
        presenter.getTeamsLeague(nameLeague)
        swipeRefresh.onRefresh {
            presenter.getTeamsLeague(nameLeague)
        }
    }

    private fun addFragment(fragment: Fragment) {
        supportFragmentManager
                .beginTransaction()
                .setCustomAnimations(R.anim.design_bottom_sheet_slide_in, R.anim.design_bottom_sheet_slide_out)
                .replace(R.id.content, fragment, fragment.javaClass.getSimpleName())
                .commit()
    }

    override fun showLoading() {
        progressBar.visible()
        progressBars.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
        progressBars.invisible()
    }

    override fun showLeagueDetail(data: List<LeagueDetail>) {
        swipeRefresh.isRefreshing = false
        val bannerLeagueImage: ImageView = findViewById(R.id.img_leaguebanner)
        val badgeLeagueImage: ImageView = findViewById(R.id.img_badgeleague)


        Picasso.get()
                .load(data[0].strBanner)
                .into(bannerLeagueImage)
        Picasso.get()
                .load(data[0].strBadge)
                .into(badgeLeagueImage)

        tv_dn_league.text = data[0].strLeague
        tv_dn_kategori.text = data[0].strSport
        tv_deksLeague.text = data[0].strDescriptionEN
    }

    override fun showTeamsDetail(data: List<Teams>) {
        swipeRefresh.isRefreshing = false
        val menuAdapter = LeagueDetailAdapter(ctx, events) {
            startActivity<TeamDetailActivity>(
                    "id" to "${it.idTeam}"
            )
        }
        events.clear()
        events.addAll(data)
        list_team.hasFixedSize()
        list_team.layoutManager = LinearLayoutManager(this)
        list_team.adapter = menuAdapter
    }
}