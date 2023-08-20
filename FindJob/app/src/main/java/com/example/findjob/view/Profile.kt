package com.example.findjob.view

import android.os.Bundle
import android.text.TextUtils.replace
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.findjob.R
import com.example.findjob.controller.Session
import com.example.findjob.databinding.FragmentProfileBinding

class Profile : Fragment() {
    lateinit var binding: FragmentProfileBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        val username = Session["username"] as String
//        val login = Login()
//        val profileDetail = ProfileDetail()
//        if (username != "") {
//            supportFragmentManager.beginTransaction().apply {
//                replace(R.id.profile, login)
//                commit()
//            }
//        } else {
//            supportFragmentManager.beginTransaction().apply {
//                replace(R.id.profile, profileDetail)
//                commit()
//            }
//        }
    }

    companion object {}
}