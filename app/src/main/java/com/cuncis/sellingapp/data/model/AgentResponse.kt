package com.cuncis.sellingapp.data.model


import com.google.gson.annotations.SerializedName


data class AgentResponse(

	@field:SerializedName("data")
	val data: List<Agent>? = null
)