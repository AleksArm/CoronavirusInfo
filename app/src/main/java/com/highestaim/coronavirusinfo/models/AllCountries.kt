package com.highestaim.coronavirusinfo.models


import com.google.gson.annotations.SerializedName

data class AllCountries(
    @SerializedName("countryitems")
    val countryItems: List<LinkedHashMap<String, CountryInAllCountriesModel>>
) {

    data class CountryInAllCountriesModel(
        @SerializedName("code")
        val code: String?, // AF
        @SerializedName("ourid")
        val ourid: Int?, // 1
        @SerializedName("source")
        val source: String?, // https://thevirustracker.com/afghanistan-coronavirus-information-af
        @SerializedName("title")
        val title: String?, // Afghanistan
        @SerializedName("total_active_cases")
        val totalActiveCases: Int?, // 756
        @SerializedName("total_cases")
        val totalCases: Int?, // 840
        @SerializedName("total_deaths")
        val totalDeaths: Int?, // 30
        @SerializedName("total_new_cases_today")
        val totalNewCasesToday: Int?, // 56
        @SerializedName("total_new_deaths_today")
        val totalNewDeathsToday: Int?, // 5
        @SerializedName("total_recovered")
        val totalRecovered: Int?, // 54
        @SerializedName("total_serious_cases")
        val totalSeriousCases: Int?, // 0
        @SerializedName("total_unresolved")
        val totalUnresolved: Int? // 0
    )
}