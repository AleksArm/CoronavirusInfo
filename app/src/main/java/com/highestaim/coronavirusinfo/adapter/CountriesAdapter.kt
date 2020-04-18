package com.highestaim.coronavirusinfo.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.RecyclerView
import com.highestaim.coronavirusinfo.R
import com.highestaim.coronavirusinfo.dto.CoronavirusInfoDto
import com.highestaim.coronavirusinfo.util.hide
import com.highestaim.coronavirusinfo.util.initRecyclerView
import com.highestaim.coronavirusinfo.util.show

import kotlinx.android.synthetic.main.statistic_inof_adapter_view.view.*
import kotlin.properties.Delegates

class CountriesAdapter : RecyclerView.Adapter<CountriesAdapter.CountriesViewHolder>(),
    AutoUpdatableAdapter {

    var countryList: List<CoronavirusInfoDto?> by Delegates.observable(emptyList()) { _, old, new ->
        autoNotify(old, new) { o, n -> o?.countryName == n?.countryName }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = CountriesViewHolder(
        LayoutInflater.from(parent.context)
            .inflate(R.layout.statistic_inof_adapter_view, parent, false)
    )

    override fun getItemCount() = countryList.size

    override fun onBindViewHolder(holder: CountriesViewHolder, position: Int) {
        val country = countryList[position]

        holder.countryName.text = country?.countryName

        holder.itemView.setOnClickListener {
            if (holder.detailRecyclerView.isShown) {
                holder.detailRecyclerView.hide()
            } else {
                holder.detailRecyclerView.show()
            }
        }

        val menuItemAdapter = holder.detailRecyclerView.adapter as CountriesInfoAdapter?

        country?.info?.let {
            if (it.isNotEmpty() && it.size != 5)
                menuItemAdapter?.infoItems = it.subList(it.size - 5, it.size).reversed()
            else
                menuItemAdapter?.infoItems = it

            menuItemAdapter?.notifyDataSetChanged()
        }

    }


    inner class CountriesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var countryName: AppCompatTextView = itemView.findViewById(R.id.countryName)
        var seeMoreButton: ImageView = itemView.findViewById(R.id.seeMore)

        val detailRecyclerView: RecyclerView = itemView.detailRecyclerView


        init {
            detailRecyclerView.itemAnimator = DefaultItemAnimator()
            detailRecyclerView.setHasFixedSize(true)
            detailRecyclerView.initRecyclerView(itemView.context, CountriesInfoAdapter())
        }
    }

}