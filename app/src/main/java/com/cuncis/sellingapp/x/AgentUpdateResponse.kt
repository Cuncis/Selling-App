package com.cuncis.sellingapp.x


import com.google.gson.annotations.SerializedName


data class AgentUpdateResponse(

	@field:SerializedName("msg")
	val msg: Msg? = null,

	@field:SerializedName("status")
	val status: Boolean? = null
)