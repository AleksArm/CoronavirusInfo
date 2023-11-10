package com.highestaim.coronavirusinfo.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.RecyclerView
import com.highestaim.coronavirusinfo.databinding.StatisticInofAdapterViewBinding
import com.highestaim.coronavirusinfo.dto.CoronavirusInfoDto
import com.highestaim.coronavirusinfo.util.hide
import com.highestaim.coronavirusinfo.util.initRecyclerView
import com.highestaim.coronavirusinfo.util.show

import kotlin.properties.Delegates

class CountriesAdapter : RecyclerView.Adapter<CountriesAdapter.CountriesViewHolder>(),
    AutoUpdatableAdapter {

    var countryList: List<CoronavirusInfoDto?> by Delegates.observable(emptyList()) { _, old, new ->
        autoNotify(old, new) { o, n -> o?.countryName == n?.countryName }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountriesViewHolder {
        return CountriesViewHolder(StatisticInofAdapterViewBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount() = countryList.size

    override fun onBindViewHolder(holder: CountriesViewHolder, position: Int) {
        val country = countryList[position] ?: CoronavirusInfoDto()
        holder.bind(country)
    }


    inner class CountriesViewHolder(private val binding: StatisticInofAdapterViewBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.detailRecyclerView.itemAnimator = DefaultItemAnimator()
            binding.detailRecyclerView.setHasFixedSize(true)
            binding.detailRecyclerView.initRecyclerView(itemView.context, CountriesInfoAdapter())
        }

        @SuppressLint("NotifyDataSetChanged")
        fun bind(country: CoronavirusInfoDto) {
            binding.countryName.text = country.countryName

            binding.root.setOnClickListener {
                if (binding.detailRecyclerView.isShown) {
                    binding.detailRecyclerView.hide()
                } else {
                    binding.detailRecyclerView.show()
                }
            }

            val menuItemAdapter = binding.detailRecyclerView.adapter as CountriesInfoAdapter?

            country.info?.let {
                if (it.isNotEmpty() && it.size != 5)
                    menuItemAdapter?.infoItems = it.subList(it.size - 5, it.size).reversed()
                else
                    menuItemAdapter?.infoItems = it

                menuItemAdapter?.notifyDataSetChanged()
            }
        }
    }

}