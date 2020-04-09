package com.highestaim.exitsheet.dto

import com.highestaim.exitsheet.models.CoronaInfoCountriesModel

data class CoronavirusInfoDto(
    var countryName: String? = null,
    var info: List<CoronaInfoCountriesModel?>? = null
)