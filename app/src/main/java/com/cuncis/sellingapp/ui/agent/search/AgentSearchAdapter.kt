package com.cuncis.sellingapp.ui.agent.search

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.recyclerview.widget.RecyclerView
import com.cuncis.sellingapp.R
import com.cuncis.sellingapp.data.model.agent.Agent
import com.cuncis.sellingapp.util.Constants
import com.infinityandroid.roundedimageview.RoundedImageView
import com.cuncis.sellingapp.util.Utils.Companion.setGlideImage
import kotlinx.android.synthetic.main.item_agent_search.view.*


class AgentSearchAdapter (val context: Context, private val agentList: ArrayList<Agent>,
                          val clickListener: (Agent, Int) -> Unit)
    : RecyclerView.Adapter<AgentSearchAdapter.AgentHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AgentHolder {
        return AgentHolder(LayoutInflater.from(context).inflate(R.layout.item_agent_search, parent, false))
    }

    override fun getItemCount(): Int = agentList.size

    override fun onBindViewHolder(holder: AgentHolder, position: Int) {
        holder.bind(agentList[position])
        context.setGlideImage(holder.imgStore, agentList[position].gambarToko!!, holder.progress)

        holder.itemView.crvAgent.setOnClickListener {
            Constants.AGENT_ID = agentList[position].kdAgen!!.toLong()
            clickListener(agentList[position], position)
        }
    }

    fun setAgentList(newAgentList: List<Agent>) {
        agentList.clear()
        agentList.addAll(newAgentList)
        notifyDataSetChanged()
    }


    inner class AgentHolder(view: View): RecyclerView.ViewHolder(view) {
        private val nameStore = view.txvNameStore
        private val location = view.txvLocation
        var imgStore: RoundedImageView = view.imvImage
        var progress: ProgressBar = view.progressBar

        fun bind(agent: Agent) {
            nameStore.text = agent.namaToko
            location.text = agent.alamat
        }
    }
}