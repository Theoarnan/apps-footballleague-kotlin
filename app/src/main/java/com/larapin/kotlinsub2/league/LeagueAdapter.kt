package com.larapin.kotlinsub2.league

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.larapin.kotlinsub2.R
import com.larapin.kotlinsub2.model.League
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_league.view.*
import okhttp3.*
import org.jetbrains.anko.sdk27.coroutines.onClick


val client = OkHttpClient()
private lateinit var presenter: LeaguePresenter

class LeagueAdapter(private val context: Context, private val events: List<League>, private val listener: (League) -> Unit)
    : RecyclerView.Adapter<LeagueViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LeagueViewHolder {
        return LeagueViewHolder(LayoutInflater.from(context).inflate(R.layout.item_league, parent, false))
    }

    override fun getItemCount(): Int = events.size

    override fun onBindViewHolder(holder: LeagueViewHolder, position: Int) {
        holder.bindItem(events[position], listener)
    }

}

class LeagueViewHolder(val view: View): RecyclerView.ViewHolder(view) {
    fun bindItem(events: League, listener: (League) -> Unit){
        val teamImage: ImageView = view.findViewById(R.id.img_league)
        Picasso.get()
                .load(events.strBadge)
                .into(teamImage)
        itemView.tv_nameleague.text = events.strLeague
        itemView.tv_ketleague.text = events.strSport
        itemView.onClick { listener(events) }
    }
}

