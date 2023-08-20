package com.example.findjob.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.findjob.R
import com.example.findjob.controller.ViewAdapterTabLayoutMediator
import com.example.findjob.databinding.ActivityMainBinding
import com.example.findjob.databinding.ActivityTablayoutBinding
import com.google.android.material.tabs.TabLayoutMediator

class TabLayoutMediator : AppCompatActivity() {
    lateinit var binding: ActivityTablayoutBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTablayoutBinding.inflate(layoutInflater)
        val adapterr =ViewAdapterTabLayoutMediator(supportFragmentManager,lifecycle)
        binding.pagerHome.adapter = adapterr
        TabLayoutMediator(binding.tabHome,binding.pagerHome){ tab,pos ->
            when(pos){
                0 -> {tab.setIcon(R.drawable.icon_home)}
                1 -> {tab.setIcon(R.drawable.icon_profile)}
                2 -> {tab.setIcon(R.drawable.icon_news)}
                3 -> {tab.setIcon(R.drawable.icon_notification)}
                4 -> {tab.setIcon(R.drawable.icon_setting)}
            }
        }.attach()
        setContentView(binding.root)
    }
}