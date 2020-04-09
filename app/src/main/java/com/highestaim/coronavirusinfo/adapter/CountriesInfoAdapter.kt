package com.highestaim.coronavirusinfo.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.highestaim.coronavirusinfo.R
import com.highestaim.exitsheet.models.CoronaInfoCountriesModel
import kotlin.properties.Delegates

class CountriesInfoAdapter : RecyclerView.Adapter<CountriesInfoAdapter.CountriesInfoViewHolder>(), AutoUpdatableAdapter {

    var infoItems: List<CoronaInfoCountriesModel?> by Delegates.observable(emptyList()) {
            prop, old, new ->
        autoNotify(old, new) { o, n -> o?.date == n?.date }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = CountriesInfoViewHolder(
        LayoutInflater.from(parent.context)
            .inflate(R.layout.detail_info_adapter_view, parent, false)
    )

    override fun getItemCount() = infoItems.size

    override fun onBindViewHolder(holder: CountriesInfoViewHolder, position: Int) {
        val itemInfo = infoItems[position]
        holder.confirmedAppCompatTextView?.text = itemInfo?.confirmed.toString()
        holder.dateAppCompatTextView?.text = itemInfo?.date
        holder.recoveredAppCompatTextView?.text = itemInfo?.recovered.toString()
        holder.deathsAppCompatTextView?.text = itemInfo?.deaths.toString()
    }


    inner class CountriesInfoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var dateAppCompatTextView: AppCompatTextView? = itemView.findViewById(R.id.date)
        var confirmedAppCompatTextView: AppCompatTextView? = itemView.findViewById(R.id.confirmed)
        var recoveredAppCompatTextView: AppCompatTextView? = itemView.findViewById(R.id.recovered)
        var deathsAppCompatTextView: AppCompatTextView? = itemView.findViewById(R.id.deaths)
    }
}
