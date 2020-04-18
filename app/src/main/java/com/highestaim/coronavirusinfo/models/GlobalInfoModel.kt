package com.highestaim.coronavirusinfo.models


import com.google.gson.annotations.SerializedName

data class GlobalInfoModel(
    @SerializedName("results")
    val results: List<Result?>?,
    @SerializedName("stat")
    val stat: String? // ok
) {
    data class Result(
        @SerializedName("source")
        val source: Source?,

        @SerializedName("total_active_cases")
        val totalActiveCases: Int?, // 1317753

        @SerializedName("total_affected_countries")
        val totalAffectedCountries: Int?, // 210

        @SerializedName("total_cases")
        val totalCases: Int?, // 1867129

        @SerializedName("total_deaths")
        val totalDeaths: Int?, // 115278

        @SerializedName("total_new_cases_today")
        val totalNewCasesToday: Int?, // 14904

        @SerializedName("total_new_deaths_today")
        val totalNewDeathsToday: Int?, // 1084

        @SerializedName("total_recovered")
        val totalRecovered: Int?, // 434098

        @SerializedName("total_serious_cases")
        val totalSeriousCases: Int?, // 50802

        @SerializedName("total_unresolved")
        val totalUnresolved: Int? // 1318512
    ) {
        data class Source(
            @SerializedName("url")
            val url: String? // https://thevirustracker.com/
        )
    }
}