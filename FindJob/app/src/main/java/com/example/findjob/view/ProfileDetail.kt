package com.example.findjob.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.navigation.fragment.findNavController
import com.example.findjob.R
import com.example.findjob.controller.ApiSever
import com.example.findjob.controller.Session
import com.example.findjob.databinding.FragmentProfileDetailBinding
import com.example.findjob.model.Account
import com.example.findjob.model.Company
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ProfileDetail : Fragment() {
    lateinit var binding:FragmentProfileDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val us = Session["username"] as String
        if(us != ""){
            ApiSever.retrofit.getAccount(us).enqueue(
                object : Callback<List<Account>> {
                    override fun onResponse(
                        call: Call<List<Account>>,
                        response: Response<List<Account>>
                    ) {
                        var user = response.body()
                        var accountUser = user?.size as Int
                        if (accountUser >  0){
                            binding.txtEmailProfileDetail.text = user[0].email
                            Log.e("Email Profile Detail: ","${user[0].email}")
                        }else{
                            Log.e("Arr Profile Detail Rỗng: ","${accountUser}")
                        }

                    }

                    override fun onFailure(call: Call<List<Account>>, t: Throwable) {
                        Log.e("Lấy dữ liệu Profile Detail Account Không thành công "," Faile")
                    }
                }
            )
        }else{
            Log.e("Lấy dữ liệu SESSION USERNAME Không thành công ","$us")
        }
        binding.btnLogout.setOnClickListener {
            Session.clear()
            Session.set("username","")
            Session.set("titlejob","")
            Session.set("location","")
            Session.set("pages",0)
            Session.set("searchblog","")
            Session.set("searchjob","")
//            findNavController().navigate(R.id.action_profileDetail_to_login)
            findNavController().navigate(R.id.action_profileDetail_to_login)
        }

        binding.txtManageCompany.setOnClickListener {
            findNavController().navigate(R.id.action_profileDetail_to_manageCompany)
        }
        binding.txtManageBlog.setOnClickListener {
            findNavController().navigate(R.id.action_profileDetail_to_manageBlog)
        }
        binding.txtManageCv.setOnClickListener {
            findNavController().navigate(R.id.action_profileDetail_to_manageCV)
        }
        binding.txtManageJob.setOnClickListener {
            findNavController().navigate(R.id.action_profileDetail_to_manageJob)
        }
        binding.txtFollowProfile.setOnClickListener {
            findNavController().navigate(R.id.action_profileDetail_to_manageFollow)
        }
        binding.txtEditProfile.setOnClickListener {
            findNavController().navigate(R.id.action_profileDetail_to_editProfile)
        }
    }


}