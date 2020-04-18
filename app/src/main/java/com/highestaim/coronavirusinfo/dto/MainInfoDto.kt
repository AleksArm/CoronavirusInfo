package com.highestaim.coronavirusinfo.dto

data class MainInfoDto(
    var totalCases: Int? = 0,
    var totalRecovered: Int? = 0,
    var totalDeaths: Int? = 0,
    var totalCasesToday: Int? = 0,
    var totalDeathsToday: Int? = 0,
    var totalSeriousCases: Int? = 0
)