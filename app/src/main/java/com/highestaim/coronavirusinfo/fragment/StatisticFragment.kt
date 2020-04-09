package com.highestaim.coronavirusinfo.fragment

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import com.highestaim.coronavirusinfo.R
import com.highestaim.coronavirusinfo.adapter.CountriesAdapter
import com.highestaim.coronavirusinfo.util.Converter.convertCoronaModelToCoronaDto
import com.highestaim.coronavirusinfo.util.initRecyclerView
import com.highestaim.coronavirusinfo.viewModel.CoronaVirusInfoViewModel
import com.highestaim.exitsheet.dto.CoronavirusInfoDto
import kotlinx.android.synthetic.main.statistic_layout.*


class StatisticFragment : BaseFragment() {

    private val countryAdapter = CountriesAdapter()
    private var coronaVirusInfoViewModel: CoronaVirusInfoViewModel? = null

    private val HEADER_COUNTRY = "Armenia"


    override fun getLayoutId() = R.layout.statistic_layout

    override fun getTitle() = "Statistic"

    override fun setupToolbar() {

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        coronaVirusInfoViewModel =
            ViewModelProviders.of(this).get(CoronaVirusInfoViewModel::class.java)
        initCountryRecyclerView()
        getCountriesList()
    }


    private fun initCountryRecyclerView() {

        val itemDecor = DividerItemDecoration(context, DividerItemDecoration.HORIZONTAL)
        context?.getDrawable(R.drawable.divider)?.let { itemDecor.setDrawable(it) }
        countryList.addItemDecoration(itemDecor)

        countryList?.initRecyclerView(context, countryAdapter)
    }

    private fun setCountries(coronaInfoCountriesModel: List<CoronavirusInfoDto?>) {
        countryAdapter.countryList = coronaInfoCountriesModel
    }

    private fun getCountriesList() {
        coronaVirusInfoViewModel?.getInfAboutCorona()?.observe(viewLifecycleOwner, Observer {
            val convertCoronaModelToCoronaDto = convertCoronaModelToCoronaDto(it)
            setCountries(convertCoronaModelToCoronaDto)
            getHeaderCountry(convertCoronaModelToCoronaDto)
        })
    }

    private fun getHeaderCountry(coronaInfoCountriesModel: List<CoronavirusInfoDto?>) {
        for (country in coronaInfoCountriesModel) {
            if (country?.countryName == HEADER_COUNTRY) {
                setHeaderCountryInfo(country)
                break
            }
        }
    }

    private fun setHeaderCountryInfo(coronaInfoCountriesModel: CoronavirusInfoDto?) {
        if (coronaInfoCountriesModel?.info?.isNotEmpty() == true) {
            val countryInfo = coronaInfoCountriesModel.info?.reversed()?.get(0)
            headerDate.text = countryInfo?.date
            headerConfirmed.text = countryInfo?.confirmed?.toString()
            headerDeaths.text = countryInfo?.deaths?.toString()
            headerRecovered.text = countryInfo?.confirmed?.toString()
        }
    }
}