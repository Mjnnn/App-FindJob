package com.example.findjob

import android.content.Intent
import com.example.findjob.model.Account
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.findjob.controller.ApiSever
import com.example.findjob.controller.Session
import com.example.findjob.databinding.ActivityMainBinding
import com.example.findjob.model.MyResponse
import com.example.findjob.view.StartJobTitle
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response



class MainActivity : AppCompatActivity(){
    lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        binding.btnStart.setOnClickListener {
            Session.set("username","")
            Session.set("pages",0)
            var i = Intent(this,StartJobTitle::class.java)
            startActivity(i)
        }
        setContentView(binding.root)
    }

}