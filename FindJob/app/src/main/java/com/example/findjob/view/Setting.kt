package com.example.findjob.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.findjob.R
import com.example.findjob.databinding.FragmentSettingBinding

class Setting : Fragment() {
        lateinit var binding: FragmentSettingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSettingBinding.inflate(inflater, container, false)
//        return inflater.inflate(R.layout.fragment_setting, container, false)
        return binding.root
    }

    companion object {
    }
}