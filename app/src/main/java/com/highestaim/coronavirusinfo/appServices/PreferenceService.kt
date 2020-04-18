package com.highestaim.coronavirusinfo.appServices

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.highestaim.coronavirusinfo.dto.MainInfoDto
import com.highestaim.coronavirusinfo.models.CoronaInfoCountriesModel

@SuppressLint("StaticFieldLeak")
class PreferenceService {

    private val allInfo = "all_info"
    private val amInfo = "am_info"
    private var context: Context? = null

    // save User Token is our main preferences, so for future we must move all preferences to save user token prefereces.
    private var allInfoSharedPreferences: SharedPreferences? = null
    private var amInfoSharedPreferences: SharedPreferences? = null

    private val gson by lazy { Gson() }


    fun injectContext(context: Context): PreferenceService {
        if (this.context == null) {
            this.context = context
            initSharedPreferences()
        }
        return get()
    }

    val mainInfo: MainInfoDto?
        get() {
            if (allInfoSharedPreferences == null) {
                return null
            }
            val s = allInfoSharedPreferences?.getString(allInfo, "")
            if (s != null && s.isEmpty()) {
                return null
            }
            return gson.fromJson(s, MainInfoDto::class.java)
        }

    @SuppressLint("ApplySharedPref")
    fun putAllInfo(mainInfoDto: MainInfoDto) {
        allInfoSharedPreferences?.edit()?.putString(allInfo, gson.toJson(mainInfoDto))?.commit()
    }


    val getAmInfo: CoronaInfoCountriesModel?
        get() {
            if (amInfoSharedPreferences == null) {
                return null
            }
            val s = amInfoSharedPreferences?.getString(amInfo, "")
            if (s != null && s.isEmpty()) {
                return null
            }
            return gson.fromJson(s, CoronaInfoCountriesModel::class.java)
        }

    @SuppressLint("ApplySharedPref")
    fun putAmInfo(amInfoDto: CoronaInfoCountriesModel) {
        amInfoSharedPreferences?.edit()?.putString(amInfo, gson.toJson(amInfoDto))?.commit()
    }

    private fun initSharedPreferences() {
        if (amInfoSharedPreferences == null) {
            amInfoSharedPreferences =
                context?.getSharedPreferences("saveAmInfo", Context.MODE_PRIVATE)
        }
        if (allInfoSharedPreferences == null) {
            allInfoSharedPreferences =
                context?.getSharedPreferences("saveAllInfo", Context.MODE_PRIVATE)
        }
    }

    companion object {
        private var preferenceService: PreferenceService? = null

        fun get(): PreferenceService {
            if (preferenceService == null) {
                preferenceService = PreferenceService()
            }
            return preferenceService as PreferenceService
        }
    }
}

