package com.highestaim.coronavirusinfo.fragment

import android.os.Build
import android.os.Bundle
import com.highestaim.coronavirusinfo.R
import com.highestaim.coronavirusinfo.appServices.PreferenceService.Companion.get
import com.highestaim.coronavirusinfo.databinding.HomeFragmentBinding
import com.highestaim.coronavirusinfo.dto.CoronavirusInfoDto
import com.highestaim.coronavirusinfo.dto.MainInfoDto
import com.highestaim.coronavirusinfo.models.CoronaInfoCountriesModel
import com.highestaim.coronavirusinfo.util.onClick
import com.highestaim.coronavirusinfo.viewModel.CoronaVirusInfoViewModel
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Date

class HomeFragment : BaseFragment<HomeFragmentBinding, CoronaVirusInfoViewModel>(
    CoronaVirusInfoViewModel::class
) {

    private val COUNTRY_CODE = "AM"

    private var convertCoronaModelToCoronaDto: ArrayList<CoronavirusInfoDto?> = arrayListOf()

    override fun getViewBinding(): HomeFragmentBinding = HomeFragmentBinding.inflate(layoutInflater)

    override fun initUI(savedInstanceState: Bundle?) {
        setInfo(get().mainInfo)
        setCountryInfo(get().getAmInfo)
        getAllInfoAboutWorld()
        getCountryInfo()
        setCurrentDate()
    }

    override fun subscribeToViewModel(viewModel: CoronaVirusInfoViewModel) {
        viewModel.getTotalAllCountries().observe(viewLifecycleOwner) {
            println(it)
        }
    }

    private fun setCurrentDate() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            binding.headerDate.text = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE)
        } else {
            binding.headerDate.text = SimpleDateFormat("yyyy.MM.dd").format(Date()).toString()
        }
    }

    override fun getTitle() = getString(R.string.home)

    override fun setupToolbar() {
    }

    private fun setOnSeeByCountryClickListener() {
        binding.viewByCountryTextView.onClick {
            openStatisticFragment(convertCoronaModelToCoronaDto)
        }
    }


    private fun getAllInfoAboutWorld() {
        viewModel.getInfoAboutWorld().observe(viewLifecycleOwner) {
            it?.let { globalInfo ->
                globalInfo.results?.let { result ->
                    if (result.isNotEmpty()) {
                        val info = MainInfoDto(
                            totalCases = result[0]?.totalCases,
                            totalRecovered = result[0]?.totalRecovered,
                            totalDeaths = result[0]?.totalDeaths,
                            totalCasesToday = result[0]?.totalNewCasesToday,
                            totalDeathsToday = result[0]?.totalNewDeathsToday
                        )
                        get().putAllInfo(info)
                        setInfo(info)
                    }
                }
            }
        }
    }

    private fun setInfo(mainInfoDto: MainInfoDto?) {
        binding.recoveredTextView.text = mainInfoDto?.totalRecovered.toString()
        binding.confirmedTextView.text = mainInfoDto?.totalCases.toString()
        binding.deathsTextView.text = mainInfoDto?.totalDeaths.toString()
        binding.confirmedToday.text = "Today ${mainInfoDto?.totalCasesToday.toString()}"
        binding.deathsToday.text = "Today ${mainInfoDto?.totalDeathsToday.toString()}"
    }


    private fun getCountryInfo() {
        viewModel.getCountyInfo(COUNTRY_CODE).observe(viewLifecycleOwner) {
            if (it?.countrydata?.isNotEmpty() == true) {
                val countryInfo = CoronaInfoCountriesModel(
                    confirmed = it.countrydata[0]?.totalCases,
                    recovered = it.countrydata[0]?.totalRecovered,
                    deaths = it.countrydata[0]?.totalDeaths,
                    toDay = it.countrydata[0]?.totalNewCasesToday
                )
                get().putAmInfo(countryInfo)
                setCountryInfo(countryInfo)
            }
        }
    }

    private fun setCountryInfo(coronaInfoCountriesModel: CoronaInfoCountriesModel?) {

        binding.headerConfirmed.text = coronaInfoCountriesModel?.confirmed?.toString()
        binding.headerDeaths.text = coronaInfoCountriesModel?.deaths?.toString()
        binding.headerRecovered.text = coronaInfoCountriesModel?.recovered?.toString()
        binding.todey.text = " + ${coronaInfoCountriesModel?.toDay?.toString()}"
    }

    private fun openStatisticFragment(coronaInfoCountriesModel: ArrayList<CoronavirusInfoDto?>) {
        /*  val statisticFragment = StatisticFragment()
          val bundle = Bundle()
          bundle.putSerializable("all_info", coronaInfoCountriesModel)
          statisticFragment.arguments = bundle
          replaceFragment(statisticFragment, true)*/
    }
}
