package com.example.findjob.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import com.example.findjob.R
import com.example.findjob.controller.CustomSpinnerAdapter
import com.example.findjob.controller.Session
import com.example.findjob.databinding.ActivitySearchJobBinding

class SearchJob : AppCompatActivity() {
    lateinit var binding: ActivitySearchJobBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchJobBinding.inflate(layoutInflater)

        val homeSeachJob = HomeDetailJob()
        val searchDetailJob = SearchDetailJob()
        Spinner()

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.home_search_job,homeSeachJob)
            commit()
        }

        binding.btnSearchJob.setOnClickListener {
            val searchJob = binding.edSearchJob.text.toString()
            if (searchJob != "" && searchJob != " ") {
                Log.e("Dữ Liệu SearchJob: ", "$searchJob")
                Session.set("searchjob", "$searchJob")
                val searchBlog2 = Session["searchjob"] as String
                Log.e("Dữ Liệu SearchJob2: ", "$searchBlog2")
                supportFragmentManager.beginTransaction().apply {
                    replace(R.id.home_search_job,searchDetailJob)
                    commit()
                }
            }else{
                supportFragmentManager.beginTransaction().apply {
                    replace(R.id.home_search_job,homeSeachJob)
                    commit()
                }
            }
        }
        setContentView(binding.root)
    }
    private fun Spinner() {
        val listNN = resources.getStringArray(R.array.sp_ChonNN)
        val listTP = resources.getStringArray(R.array.TinhThanh)
        val adtNN = ArrayAdapter(this,R.layout.layout_txt_sp, listNN)
        val adtTP = ArrayAdapter(this,R.layout.layout_txt_sp, listTP)
//      val adapterrNN = CustomSpinnerAdapter(this, listNN.toList())
//      val adapterrTP = CustomSpinnerAdapter(this,listTP.toList())
        //gọi spiner
        binding.spnNgangNghe.adapter = adtNN
        binding.spnDiaDiem.adapter = adtTP
    }
}