package com.highestaim.coronavirusinfo.DI


import com.highestaim.coronavirusinfo.appServices.PreferenceService
import com.highestaim.coronavirusinfo.repository.CoronaInfoRepository
import com.highestaim.coronavirusinfo.service.CoronaVirusInfoService
import com.highestaim.coronavirusinfo.viewModel.CoronaVirusInfoViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val appRepositories: Module = module {
    single { CoronaInfoRepository(get()) }
}


val Services: Module = module {
    single { PreferenceService.get().injectContext(get()) }
    single { CoronaVirusInfoService.create() }
}

val appViewModels: Module = module {
    viewModel { CoronaVirusInfoViewModel(get()) }
}