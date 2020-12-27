package com.larapin.kotlinsub2.event
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import android.text.TextUtils
import android.view.*
import android.widget.ProgressBar
import android.widget.TextView
import com.google.gson.Gson
import com.larapin.kotlinsub2.R
import com.larapin.kotlinsub2.api.ApiRepository
import com.larapin.kotlinsub2.model.Event
import com.larapin.kotlinsub2.detail.event.EventDetailActivity
import com.larapin.kotlinsub2.util.invisible
import com.larapin.kotlinsub2.util.visible
import kotlinx.android.synthetic.main.fragment_event.*
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.startActivity

class EventFragment : Fragment(), EventView {

    private lateinit var listEvent: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var tvKosong: TextView
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private var events: MutableList<Event> = mutableListOf()
    private lateinit var presenter: EventPresenter
    private lateinit var adapter: EventAdapter
    var event: String? = ""
    var event2: String? = ""
    private lateinit var searchView: SearchView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_event, container, false)
        listEvent = view.findViewById(R.id.list_event)
        progressBar = view.findViewById(R.id.progress_bar)
        tvKosong = view.findViewById(R.id.tv_empty)
        swipeRefresh = view.findViewById(R.id.swipe_refresh)
        setHasOptionsMenu(true)
        event = arguments?.getString("event")
        event2 = arguments?.getString("event2")

        adapter = EventAdapter(ctx, events){
            startActivity<EventDetailActivity>(
                    "id" to "${it.eventId}",
                    "idhome" to "${it.idHome}",
                    "idaway" to "${it.idAway}"
            )
        }
        listEvent.adapter = adapter
        val request = ApiRepository()
        val gson = Gson()
        presenter = EventPresenter(this, request, gson)
        presenter.getEventList(event2, event)
        swipeRefresh.onRefresh {
            presenter.getEventList(event2, event)
        }
        return view
    }

    override fun showLoading() {
        progressBar.visible()
        tvKosong.invisible()
        listEvent.invisible()

    }

    override fun hideLoading() {
        progressBar.invisible()
        listEvent.visible()
        tvKosong.invisible()
    }

    override fun showEmptyData() {
        progressBar.invisible()
        tvKosong.visible()
        listEvent.invisible()

    }

    override fun showEventList(data: List<Event>) {
        swipeRefresh.isRefreshing = false
        events.clear()
        events.addAll(data)
        adapter.notifyDataSetChanged()
    }

    companion object {
        fun newInstance(event: String?, id: String?): EventFragment {
            val fragment = EventFragment()
            val args = Bundle()
            args.putString("event",event)
            args.putString("event2",id)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.dashboard, menu)

        val searchItem = menu?.findItem(R.id.action_search)
        searchView = searchItem?.actionView as SearchView
        searchView.queryHint = "Search Match"
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextChange(newText: String?): Boolean {
                if (TextUtils.isEmpty(newText)){
                    presenter.getEventList("4328", event)
                }else{
                    presenter.getEventSearch(newText?.replace(" ", "_"))
                }
                return true
            }
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

        })
    }
}
