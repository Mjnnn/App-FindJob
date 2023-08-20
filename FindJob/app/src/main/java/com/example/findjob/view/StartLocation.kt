package com.example.findjob.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.findjob.R
import com.example.findjob.controller.Session
import com.example.findjob.databinding.ActivityStartJobTitleBinding
import com.example.findjob.databinding.ActivityStartLocationBinding

class StartLocation : AppCompatActivity() {
    lateinit var binding: ActivityStartLocationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStartLocationBinding.inflate(layoutInflater)
        binding.btnStartLocation.setOnClickListener {
            var locationJob = binding.edStartLocation.text
            Session.set("location","$locationJob")
            Toast.makeText(this,"Chào mừng bạn đến với FindJob",Toast.LENGTH_LONG).show()
            var i = Intent(this,TabLayoutMediator::class.java)
            startActivity(i)
        }
        setContentView(binding.root)
    }
}