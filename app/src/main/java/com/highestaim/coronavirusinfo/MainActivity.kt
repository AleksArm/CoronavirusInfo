package com.highestaim.coronavirusinfo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.highestaim.coronavirusinfo.fragment.HomeFragment
import com.highestaim.coronavirusinfo.fragment.StatisticFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        openMainPage()
    }

    private fun openMainPage() {
        supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer, HomeFragment()).commitAllowingStateLoss()
    }
}
