package com.example.findjob.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.findjob.R
import com.example.findjob.controller.Session
import com.example.findjob.databinding.FragmentCheckLoginBinding


class CheckLogin : Fragment() {

    lateinit var binding:FragmentCheckLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCheckLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val user = Session["username"] as String

        if(user != ""){
            findNavController().navigate(R.id.action_checkLogin_to_notificationDetail)
        }

        binding.btnLoginNotification.setOnClickListener {
            Session.set("pages",1)
            findNavController().navigate(R.id.action_checkLogin_to_login2)
        }

        binding.btnSignInNotification.setOnClickListener {
            Session.set("pages",1)
            findNavController().navigate(R.id.action_checkLogin_to_signInDetail2)
        }

    }

    companion object {}
}