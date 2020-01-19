package com.cuncis.sellingapp.ui.agent

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.recyclerview.widget.RecyclerView
import com.cuncis.sellingapp.R
import com.cuncis.sellingapp.data.model.Agent
import com.infinityandroid.roundedimageview.RoundedImageView
import kotlinx.android.synthetic.main.item_agent.view.*
import com.cuncis.sellingapp.util.Utils.Companion.setGlideImage

class AgentAdapter (val context: Context, private val agentList: ArrayList<Agent>): RecyclerView.Adapter<AgentAdapter.AgentHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AgentHolder {
        return AgentHolder(LayoutInflater.from(context).inflate(R.layout.item_agent, parent, false))
    }

    override fun getItemCount(): Int = agentList.size

    override fun onBindViewHolder(holder: AgentHolder, position: Int) {
        holder.bind(agentList[position])
        context.setGlideImage(holder.imgStore, agentList[position].gambarToko!!, holder.progress)
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