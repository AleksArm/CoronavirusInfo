package com.highestaim.coronavirusinfo.fragment

import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.highestaim.coronavirusinfo.R
import com.highestaim.coronavirusinfo.appServices.PreferenceService.Companion.get
import com.highestaim.coronavirusinfo.dto.CoronavirusInfoDto
import com.highestaim.coronavirusinfo.dto.MainInfoDto
import com.highestaim.coronavirusinfo.models.CoronaInfoCountriesModel
import com.highestaim.coronavirusinfo.util.onClick
import com.highestaim.coronavirusinfo.util.replaceFragment
import com.highestaim.coronavirusinfo.viewModel.CoronaVirusInfoViewModel
import kotlinx.android.synthetic.main.home_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class HomeFragment : BaseFragment() {

    private val COUNTRY_CODE = "AM"


    private val coronaVirusInfoViewModel: CoronaVirusInfoViewModel? by viewModel()
    private var convertCoronaModelToCoronaDto: ArrayList<CoronavirusInfoDto?> = arrayListOf()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        //set info from local
        setInfo(get().mainInfo)
        setCountryInfo(get().getAmInfo)

        //get info from remote
        getAllInfoAboutWorld()
        getCountryInfo()
        setCurrentDate()
        coronaVirusInfoViewModel?.getTotalAllCountries()?.observe(viewLifecycleOwner, Observer {
            println(it)
        })

        //setOnSeeByCountryClickListener()

    }

    private fun setCurrentDate() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            headerDate.text = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE)
        } else {
            headerDate.text = SimpleDateFormat("yyyy.MM.dd").format(Date()).toString()
        }
    }

    override fun getLayoutId() = R.layout.home_fragment

    override fun getTitle() = getString(R.string.home)

    override fun setupToolbar() {
    }

    private fun setOnSeeByCountryClickListener() {
        viewByCountryTextView?.onClick {
            openStatisticFragment(convertCoronaModelToCoronaDto)
        }
    }


    private fun getAllInfoAboutWorld() {
        coronaVirusInfoViewModel?.getInfoAboutWorld()?.observe(viewLifecycleOwner, Observer {
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
        })
    }

    private fun setInfo(mainInfoDto: MainInfoDto?) {
        recoveredTextView.text = mainInfoDto?.totalRecovered.toString()
        confirmedTextView.text = mainInfoDto?.totalCases.toString()
        deathsTextView.text = mainInfoDto?.totalDeaths.toString()
        confirmedToday.text = "Today ${mainInfoDto?.totalCasesToday.toString()}"
        deathsToday.text = "Today ${mainInfoDto?.totalDeathsToday.toString()}"
    }


    private fun getCountryInfo() {
        coronaVirusInfoViewModel?.getCountyInfo(COUNTRY_CODE)
            ?.observe(viewLifecycleOwner, Observer {
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
            })
    }

    private fun setCountryInfo(coronaInfoCountriesModel: CoronaInfoCountriesModel?) {

        headerConfirmed.text = coronaInfoCountriesModel?.confirmed?.toString()
        headerDeaths.text = coronaInfoCountriesModel?.deaths?.toString()
        headerRecovered.text = coronaInfoCountriesModel?.recovered?.toString()
        todey.text = " + ${coronaInfoCountriesModel?.toDay?.toString()}"
    }

    private fun openStatisticFragment(coronaInfoCountriesModel: ArrayList<CoronavirusInfoDto?>) {
        val statisticFragment = StatisticFragment()
        val bundle = Bundle()
        bundle.putSerializable("all_info", coronaInfoCountriesModel)
        statisticFragment.arguments = bundle
        replaceFragment(statisticFragment, true)
    }
}
