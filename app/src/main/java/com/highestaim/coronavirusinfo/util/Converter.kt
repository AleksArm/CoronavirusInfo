package com.highestaim.coronavirusinfo.util

import com.highestaim.coronavirusinfo.dto.CoronavirusInfoDto
import com.highestaim.coronavirusinfo.models.CoronaInfoCountriesModel
import java.util.*
import kotlin.collections.ArrayList

object Converter {

    fun convertCoronaModelToCoronaDto(coronaInfoCountriesModel: LinkedHashMap<String?, List<CoronaInfoCountriesModel?>>)
            : ArrayList<CoronavirusInfoDto?> {

        val info = arrayListOf<CoronavirusInfoDto?>()

        coronaInfoCountriesModel.forEach {
            info.add(CoronavirusInfoDto(countryName = it.key, info = it.value))
        }

        return info

    }
}