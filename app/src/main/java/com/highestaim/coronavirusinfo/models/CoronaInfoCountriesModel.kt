package com.highestaim.coronavirusinfo.models


import com.google.gson.annotations.SerializedName

data class CoronaInfoCountriesModel(

        @SerializedName("date")
        val date: String? = "",

        @SerializedName("confirmed")
        val confirmed: Int?,

        @SerializedName("deaths")
        val deaths: Int?,

        @SerializedName("recovered")
        val recovered: Int?,

        val toDay: Int?
)