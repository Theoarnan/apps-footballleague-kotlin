package com.larapin.kotlinsub2.detail.league

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.larapin.kotlinsub2.R
import com.larapin.kotlinsub2.model.Teams
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_teams.view.*
import org.jetbrains.anko.sdk27.coroutines.onClick

class LeagueDetailAdapter(private val context: Context, private val events: List<Teams>,  private val listener: (Teams) -> Unit)
    : RecyclerView.Adapter<EventViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        return EventViewHolder(LayoutInflater.from(context).inflate(R.layout.item_teams, parent, false))
    }

    override fun getItemCount(): Int = events.size

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        holder.bindItem(events[position], listener)
    }

}

class EventViewHolder(view: View): RecyclerView.ViewHolder(view) {
    fun bindItem(events: Teams, listener: (Teams) -> Unit){
        val teamImage: ImageView = itemView.findViewById(R.id.img_teams)
        Picasso.get()
                .load(events.strTeamBadge)
                .into(teamImage)
        itemView.tv_nameteams.text = events.strTeam
        itemView.onClick { listener(events) }
    }
}