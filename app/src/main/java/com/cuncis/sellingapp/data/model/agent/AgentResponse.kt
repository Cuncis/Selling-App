package com.cuncis.sellingapp.data.model.agent


import com.cuncis.sellingapp.data.model.agent.Agent
import com.google.gson.annotations.SerializedName


data class AgentResponse(

	@field:SerializedName("data")
	val data: List<Agent>? = null
)