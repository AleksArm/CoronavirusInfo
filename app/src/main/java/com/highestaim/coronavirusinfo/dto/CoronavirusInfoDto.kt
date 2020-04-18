package com.highestaim.coronavirusinfo.dto
import com.highestaim.coronavirusinfo.models.CoronaInfoCountriesModel
import java.io.Serializable

data class CoronavirusInfoDto(
    var countryName: String? = null,
    var info: List<CoronaInfoCountriesModel?>? = arrayListOf()
) : Serializable