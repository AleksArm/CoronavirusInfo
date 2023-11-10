package com.highestaim.coronavirusinfo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.highestaim.coronavirusinfo.databinding.ActivityMainBinding
import com.highestaim.coronavirusinfo.fragment.HomeFragment
import com.highestaim.coronavirusinfo.fragment.StatisticFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(ActivityMainBinding.inflate(layoutInflater).root)

        openMainPage()
    }

    private fun openMainPage() {
        supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer, HomeFragment()).commitAllowingStateLoss()
    }
}
