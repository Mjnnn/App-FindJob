package com.example.findjob.controller

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.findjob.view.*

class ViewAdapterTabLayoutMediator(fragmentManager: FragmentManager, lifecycle: Lifecycle): FragmentStateAdapter(fragmentManager,lifecycle) {
    override fun getItemCount(): Int {
        return 5
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                Home()
            }
            1 -> {
                val us = Session["username"] as String
                if(us != ""){
                    Log.e("SESSION USERNAME tablayoutProfileDetail: ","$us _ ${Session["username"] as String}")
                    ProfileDetail()
                }else{
                    Log.e("SESSION USERNAME tablayoutProfile: ","$us _ ${Session["username"] as String}")
                    Profile()
                }

            }
            2 -> {
                Blog()
            }
            3 -> {
                Notification()
            }
            else -> {
                Setting()
            }
        }
    }
}