package com.cuncis.sellingapp.data.model.agent


import com.google.gson.annotations.SerializedName


data class AgentUpdateResponse(

	@field:SerializedName("msg")
	val msg: String? = null,

	@field:SerializedName("status")
	val status: Boolean? = null
)