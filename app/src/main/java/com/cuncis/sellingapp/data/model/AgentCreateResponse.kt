package com.cuncis.sellingapp.data.model


import com.google.gson.annotations.SerializedName


data class AgentCreateResponse(

	@field:SerializedName("msg")
	val msg: CreateMsg? = null,

	@field:SerializedName("status")
	val status: Boolean? = null
)