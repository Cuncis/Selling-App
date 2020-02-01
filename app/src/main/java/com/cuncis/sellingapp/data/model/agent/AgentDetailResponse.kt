package com.cuncis.sellingapp.data.model.agent


import com.cuncis.sellingapp.data.model.agent.AgentDetail
import com.google.gson.annotations.SerializedName


data class AgentDetailResponse(

	@field:SerializedName("data")
	val data: AgentDetail? = null
)