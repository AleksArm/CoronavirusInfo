package com.highestaim.coronavirusinfo.util

import com.highestaim.exitsheet.dto.CoronavirusInfoDto
import com.highestaim.exitsheet.models.CoronaInfoCountriesModel
import java.util.*

object Converter {

    fun convertCoronaModelToCoronaDto(coronaInfoCountriesModel: LinkedHashMap<String?, List<CoronaInfoCountriesModel?>>)
            : List<CoronavirusInfoDto?> {

        val info = arrayListOf<CoronavirusInfoDto>()

        coronaInfoCountriesModel.forEach {
            info.add(CoronavirusInfoDto(countryName = it.key, info = it.value))
        }

        return info

    }
}