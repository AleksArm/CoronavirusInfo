package com.highestaim.coronavirusinfo.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.highestaim.coronavirusinfo.repository.CoronaInfoRepository
import com.highestaim.exitsheet.models.CoronaInfoCountriesModel
import java.util.LinkedHashMap

class CoronaVirusInfoViewModel : ViewModel() {

    private val coronaInfoRepository = CoronaInfoRepository()


    fun getInfAboutCorona() : LiveData<LinkedHashMap<String?, List<CoronaInfoCountriesModel?>>> {
        return coronaInfoRepository.getInfAboutCorona()
    }


}