package com.larapin.kotlinsub2.league

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import com.google.gson.Gson
import com.larapin.kotlinsub2.R
import com.larapin.kotlinsub2.api.ApiRepository
import com.larapin.kotlinsub2.detail.league.LeagueDetailActivity
import com.larapin.kotlinsub2.model.League
import com.larapin.kotlinsub2.util.invisible
import com.larapin.kotlinsub2.util.visible
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.startActivity

    class LeagueFragment : Fragment(), LeagueView {

        private lateinit var listEvent: RecyclerView
        private lateinit var progressBar: ProgressBar
        private lateinit var swipeRefresh: SwipeRefreshLayout
        private var events: MutableList<League> = mutableListOf()
        private lateinit var presenter: LeaguePresenter
        private lateinit var adapter: LeagueAdapter
        var event: String? = ""

//    private lateinit var searchView: SearchView

        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                                  savedInstanceState: Bundle?): View? {
            // Inflate the layout for this fragment
            val view: View = inflater.inflate(R.layout.fragment_league, container, false)
            listEvent = view.findViewById(R.id.list_league)
            progressBar = view.findViewById(R.id.progress_bar)
            swipeRefresh = view.findViewById(R.id.swipe_refresh)
//        setHasOptionsMenu(true)
            event = arguments?.getString("event")

            adapter = LeagueAdapter(ctx, events){
                startActivity<LeagueDetailActivity>(
                        "id" to "${it.idLeague}",
                        "name" to "${it.strLeague}"

                )
            }

            listEvent.adapter = adapter
            val request = ApiRepository()
            val gson = Gson()
            presenter = LeaguePresenter(this, request, gson)
            presenter.getLeagueList()
            swipeRefresh.onRefresh {
                presenter.getLeagueList()
            }

            return view
        }

        override fun showLoading() {
            progressBar.visible()
        }

        override fun hideLoading() {
            progressBar.invisible()
        }

        override fun showLeagueList(data: List<League>) {
            swipeRefresh.isRefreshing = false

            events.clear()
            events.addAll(data)
            adapter.notifyDataSetChanged()
        }

        companion object {
            fun newInstance(event: String?): LeagueFragment {
                val fragment = LeagueFragment()
                val args = Bundle()
                args.putString("leagues",event)
                fragment.arguments = args
                return fragment
            }
        }
}