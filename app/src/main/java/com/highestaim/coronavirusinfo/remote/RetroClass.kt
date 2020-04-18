package com.highestaim.coronavirusinfo.remote

import com.google.gson.GsonBuilder
import com.highestaim.coronavirusinfo.service.CoronaVirusInfoService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetroClass {

    private const val BASE_URL = "https://pomber.github.io/covid19/"
    private const val BASE_URL_2 = "https://api.thevirustracker.com/"


    val retroInstance: Retrofit
        get() {
            val builder = GsonBuilder()
            builder.excludeFieldsWithoutExposeAnnotation()
            val gson = GsonBuilder().setLenient().create()
            return Retrofit.Builder()
                .baseUrl(BASE_URL_2)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
        }

}