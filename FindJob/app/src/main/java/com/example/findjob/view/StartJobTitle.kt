package com.example.findjob.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.findjob.R
import com.example.findjob.controller.Session
import com.example.findjob.databinding.ActivityMainBinding
import com.example.findjob.databinding.ActivityStartJobTitleBinding

class StartJobTitle : AppCompatActivity() {
    lateinit var binding:ActivityStartJobTitleBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStartJobTitleBinding.inflate(layoutInflater)
        binding.btnStartJobTitle.setOnClickListener {
            var titleJob = binding.edStartJobTitle.text
            Session.set("titlejob","$titleJob")
            Log.e("Dữ Liệu Title: ","$titleJob")
            var i = Intent(this,StartLocation::class.java)
            startActivity(i)
        }
        setContentView(binding.root)
    }
}