package com.highestaim.coronavirusinfo.service

import com.highestaim.exitsheet.models.CoronaInfoCountriesModel
import retrofit2.Call
import retrofit2.http.GET
import java.util.LinkedHashMap

interface CoronaVirusInfoService {

    @GET("timeseries.json")
    fun getInfoAboutCorona(): Call<LinkedHashMap<String?, List<CoronaInfoCountriesModel?>>>?
}