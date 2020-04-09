package com.highestaim.coronavirusinfo.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.highestaim.coronavirusinfo.remote.RetroClass
import com.highestaim.exitsheet.models.CoronaInfoCountriesModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.LinkedHashMap

class CoronaInfoRepository {


    fun getInfAboutCorona(): LiveData<LinkedHashMap<String?, List<CoronaInfoCountriesModel?>>> {

        val info = MutableLiveData<LinkedHashMap<String?, List<CoronaInfoCountriesModel?>>>()

        partnersByTypeService.getInfoAboutCorona()
            ?.enqueue(object : Callback<LinkedHashMap<String?, List<CoronaInfoCountriesModel?>>> {
                override fun onResponse(
                    call: Call<LinkedHashMap<String?,  List<CoronaInfoCountriesModel?>>>?,
                    response: Response<LinkedHashMap<String?, List<CoronaInfoCountriesModel?>>?>
                ) {
                    info.value = response.body()
                }

                override fun onFailure(call: Call<LinkedHashMap<String?,  List<CoronaInfoCountriesModel?>>>, t: Throwable) {
                    print(t.localizedMessage)
                }
            })

        return info
    }


    companion object {
        private val partnersByTypeService = RetroClass.coronaVirusInfoService
    }
}