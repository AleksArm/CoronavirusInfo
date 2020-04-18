package com.highestaim.coronavirusinfo.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.google.gson.internal.LinkedTreeMap
import com.google.gson.reflect.TypeToken
import com.highestaim.coronavirusinfo.models.AllCountries
import com.highestaim.coronavirusinfo.models.AllCountries.CountryInAllCountriesModel
import com.highestaim.coronavirusinfo.models.CoronaInfoCountriesModel
import com.highestaim.coronavirusinfo.models.Country
import com.highestaim.coronavirusinfo.models.GlobalInfoModel
import com.highestaim.coronavirusinfo.service.CoronaVirusInfoService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class CoronaInfoRepository(private val coronaVirusInfoService: CoronaVirusInfoService) {


    fun getInfAboutCorona(): LiveData<LinkedHashMap<String?, List<CoronaInfoCountriesModel?>>> {

        val info = MutableLiveData<LinkedHashMap<String?, List<CoronaInfoCountriesModel?>>>()

        coronaVirusInfoService.getInfoAboutCorona()
            ?.enqueue(object : Callback<LinkedHashMap<String?, List<CoronaInfoCountriesModel?>>> {
                override fun onResponse(
                    call: Call<LinkedHashMap<String?, List<CoronaInfoCountriesModel?>>>?,
                    response: Response<LinkedHashMap<String?, List<CoronaInfoCountriesModel?>>?>
                ) {
                    info.value = response.body()
                }

                override fun onFailure(
                    call: Call<LinkedHashMap<String?, List<CoronaInfoCountriesModel?>>>,
                    t: Throwable
                ) {
                    print(t.localizedMessage)
                }
            })

        return info
    }

    fun getAllInfo(): LiveData<GlobalInfoModel?> {

        val info = MutableLiveData<GlobalInfoModel?>()

        coronaVirusInfoService.getAllInfo().enqueue(object : Callback<GlobalInfoModel?> {

            override fun onResponse(call: Call<GlobalInfoModel?>, response: Response<GlobalInfoModel?>) {
                info.value = response.body()
            }

            override fun onFailure(call: Call<GlobalInfoModel?>, t: Throwable) {
                t.printStackTrace()
            }
        })

        return info
    }


    fun getInfoByCountry(countryCode: String): LiveData<Country?> {

        val info = MutableLiveData<Country?>()

        coronaVirusInfoService.getByCountry(countryCode).enqueue(object : Callback<Country?> {
            override fun onResponse(call: Call<Country?>, response: Response<Country?>) {
                info.value = response.body()
            }

            override fun onFailure(call: Call<Country?>, t: Throwable) {
                t.printStackTrace()
            }

        })

        return info
    }


    fun getTotalAllCountries(): LiveData<LinkedHashMap<String,CountryInAllCountriesModel?>> {

        val info = MutableLiveData<LinkedHashMap<String,CountryInAllCountriesModel?>>()

        coronaVirusInfoService.getTotalAllCountries().enqueue(object : Callback<JsonElement?> {

            override fun onResponse(call: Call<JsonElement?>, response: Response <JsonElement?>) {

                val values = Gson().fromJson<Map<String, CountryInAllCountriesModel>>(
                    (response.body() as JsonObject).get("countryitems").asJsonArray[0], object :
                        TypeToken<Map<Any, Any>>() {}.type
                ).values

                values.forEach{
                    println(it)
                }
               // info.value = values as LinkedHashMap<String, CountryInAllCountriesModel?>

                print(response.body())
            }

            override fun onFailure(call: Call<JsonElement?>, t: Throwable) {
                println(t.localizedMessage)
            }
        })
        return info
    }
}