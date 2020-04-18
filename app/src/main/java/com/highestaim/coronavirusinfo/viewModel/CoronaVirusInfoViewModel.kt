package com.highestaim.coronavirusinfo.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.highestaim.coronavirusinfo.models.*
import com.highestaim.coronavirusinfo.repository.CoronaInfoRepository
import java.util.*

class CoronaVirusInfoViewModel(private val coronaInfoRepository: CoronaInfoRepository) : ViewModel() {




    fun getInfAboutCorona(): LiveData<LinkedHashMap<String?, List<CoronaInfoCountriesModel?>>> {
        return coronaInfoRepository.getInfAboutCorona()
    }


    fun getInfoAboutWorld() : LiveData<GlobalInfoModel?> {
        return coronaInfoRepository.getAllInfo()
    }

    fun getCountyInfo(countryInfo: String)  : LiveData<Country?>{
        return coronaInfoRepository.getInfoByCountry(countryInfo)
    }

    fun getTotalAllCountries(): LiveData<List<AllCountries.CountryInAllCountriesModel?>> {

        val data = MutableLiveData<List<AllCountries.CountryInAllCountriesModel?>>()


         val infOLinkedTreeMap = coronaInfoRepository.getTotalAllCountries()

        infOLinkedTreeMap.value


        return data
    }

    fun getAllInfo(coronaInfoCountriesModel: LinkedHashMap<String?, List<CoronaInfoCountriesModel?>>): Triple<Int, Int, Int> {
        var confirmed = 0
        var recovered = 0
        var deaths = 0

        coronaInfoCountriesModel.forEach {
            if (it.value.isNotEmpty())
                it.value.reversed()[0]?.confirmed?.let { conf ->
                    it.value.reversed()[0]?.recovered?.let { rec ->
                        it.value.reversed()[0]?.deaths?.let { det ->
                            confirmed = confirmed.plus(conf)
                            recovered = recovered.plus(rec)
                            deaths = deaths.plus(det)
                        }
                    }
                }
        }

        return Triple(confirmed, recovered, deaths)
    }
}