package com.highestaim.coronavirusinfo.models


import com.google.gson.annotations.SerializedName

data class Country(
    @SerializedName("countrydata")
    val countrydata: List<Countrydata?>?,
    @SerializedName("stat")
    val stat: String? // ok
) {
    data class Countrydata(

        @SerializedName("info")
        val info: Info?,

        @SerializedName("total_active_cases")
        val totalActiveCases: Int?, // 814

        @SerializedName("total_cases")
        val totalCases: Int?, // 1039

        @SerializedName("total_danger_rank")
        val totalDangerRank: Int?, // 68

        @SerializedName("total_deaths")
        val totalDeaths: Int?, // 14

        @SerializedName("total_new_cases_today")
        val totalNewCasesToday: Int?, // 26

        @SerializedName("total_new_deaths_today")
        val totalNewDeathsToday: Int?, // 1

        @SerializedName("total_recovered")
        val totalRecovered: Int?, // 211

        @SerializedName("total_serious_cases")
        val totalSeriousCases: Int?, // 30

        @SerializedName("total_unresolved")
        val totalUnresolved: Int? // 0
    ) {
        data class Info(
            @SerializedName("code")
            val code: String?, // AM
            @SerializedName("ourid")
            val ourid: Int?, // 6
            @SerializedName("source")
            val source: String?, // https://thevirustracker.com/armenia-coronavirus-information-am
            @SerializedName("title")
            val title: String? // Armenia
        )
    }
}