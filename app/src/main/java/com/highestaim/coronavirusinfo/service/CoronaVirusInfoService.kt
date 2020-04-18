package com.highestaim.coronavirusinfo.service

import com.google.gson.JsonElement
import com.highestaim.coronavirusinfo.models.AllCountries
import com.highestaim.coronavirusinfo.models.CoronaInfoCountriesModel
import com.highestaim.coronavirusinfo.models.Country
import com.highestaim.coronavirusinfo.models.GlobalInfoModel
import com.highestaim.coronavirusinfo.remote.RetroClass
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.*

interface CoronaVirusInfoService {

    @GET("timeseries.json")
    fun getInfoAboutCorona(): Call<LinkedHashMap<String?, List<CoronaInfoCountriesModel?>>>?


    @GET("free-api")
    fun getAllInfo(@Query("global") type: String = "stats"): Call<GlobalInfoModel?>

    @GET("free-api")
    fun getByCountry(@Query("countryTotal") code: String = "AM"): Call<Country?>


    @GET("free-api")
    fun getTotalAllCountries(@Query("countryTotals") type: String = "ALL"): Call<JsonElement?>

    companion object {
        fun create(): CoronaVirusInfoService {
            return RetroClass.retroInstance.create(CoronaVirusInfoService::class.java)
        }
    }

}