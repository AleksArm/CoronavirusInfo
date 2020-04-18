package com.highestaim.coronavirusinfo.fragment

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import com.highestaim.coronavirusinfo.R
import com.highestaim.coronavirusinfo.adapter.CountriesAdapter
import com.highestaim.coronavirusinfo.dto.CoronavirusInfoDto
import com.highestaim.coronavirusinfo.util.initRecyclerView
import kotlinx.android.synthetic.main.statistic_fragment.*
import java.lang.Exception


class StatisticFragment : BaseFragment() {

    private val countryAdapter = CountriesAdapter()


    override fun getLayoutId() = R.layout.statistic_fragment

    override fun getTitle() = "Statistic"

    override fun setupToolbar() {

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initCountryRecyclerView()
        setCountries(arguments?.getSerializable("all_info") as ArrayList<CoronavirusInfoDto?>)
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

}