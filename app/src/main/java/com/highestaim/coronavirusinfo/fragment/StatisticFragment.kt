package com.highestaim.coronavirusinfo.fragment

import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import com.highestaim.coronavirusinfo.R
import com.highestaim.coronavirusinfo.adapter.CountriesAdapter
import com.highestaim.coronavirusinfo.databinding.StatisticFragmentBinding
import com.highestaim.coronavirusinfo.dto.CoronavirusInfoDto
import com.highestaim.coronavirusinfo.util.initRecyclerView
import com.highestaim.coronavirusinfo.viewModel.EmptyViewModel


class StatisticFragment : BaseFragment<StatisticFragmentBinding, EmptyViewModel>(
    EmptyViewModel::class
) {

    private val countryAdapter = CountriesAdapter()
    override fun getViewBinding(): StatisticFragmentBinding = StatisticFragmentBinding.inflate(layoutInflater)

    override fun initUI(savedInstanceState: Bundle?) {
        initCountryRecyclerView()
        setCountries(arguments?.getSerializable("all_info") as ArrayList<CoronavirusInfoDto?>)
    }

    override fun subscribeToViewModel(viewModel: EmptyViewModel) {
    }

    override fun getTitle() = getString(R.string.label_statistic)

    override fun setupToolbar() {

    }

    private fun initCountryRecyclerView() {
        val itemDecor = DividerItemDecoration(context, DividerItemDecoration.HORIZONTAL)
        ContextCompat.getDrawable(requireContext(),R.drawable.divider)
            ?.let { itemDecor.setDrawable(it) }
        binding.countryList.addItemDecoration(itemDecor)

        binding.countryList.initRecyclerView(context, countryAdapter)
    }

    private fun setCountries(coronaInfoCountriesModel: List<CoronavirusInfoDto?>) {
        countryAdapter.countryList = coronaInfoCountriesModel
    }
}