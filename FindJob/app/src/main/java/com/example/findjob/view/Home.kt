package com.example.findjob.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.findjob.R
import com.example.findjob.databinding.FragmentHomeBinding

class Home : Fragment() {
    lateinit var binding: FragmentHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
    binding = FragmentHomeBinding.inflate(inflater, container, false)
//        return inflater.inflate(R.layout.fragment_home, container, false)
    return binding.root
    }

    companion object {

    }
}