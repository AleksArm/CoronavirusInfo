package com.highestaim.coronavirusinfo.remote

import com.google.gson.GsonBuilder
import com.highestaim.coronavirusinfo.service.CoronaVirusInfoService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetroClass {

    private const val BASE_URL = "https://pomber.github.io/covid19/"


    private val retroInstance: Retrofit
        get() {
            val builder = GsonBuilder()
            builder.excludeFieldsWithoutExposeAnnotation()
            val gson = GsonBuilder().setLenient().create()
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
        }


    val coronaVirusInfoService: CoronaVirusInfoService
        get() = retroInstance.create(CoronaVirusInfoService::class.java)
}