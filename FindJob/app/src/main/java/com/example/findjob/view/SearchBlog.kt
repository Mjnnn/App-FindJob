package com.example.findjob.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.findjob.R
import com.example.findjob.controller.Session
import com.example.findjob.databinding.ActivityMainBinding
import com.example.findjob.databinding.ActivitySearchBlogBinding

class SearchBlog : AppCompatActivity() {
    lateinit var binding: ActivitySearchBlogBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBlogBinding.inflate(layoutInflater)
        val homeSearchBlog = HomeSearchBlog()
        val searchDetailBlog = SearchDetailBlog()

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.home_search_blog,homeSearchBlog)
            commit()
        }

        binding.btnSearchBlog.setOnClickListener {
            var searchBlog = binding.edSearchBlog.text.toString()
            if (searchBlog != "" && searchBlog != " "){
                Log.e("Dữ Liệu SearchBlog: ","$searchBlog")
                Session.set("searchblog","$searchBlog")
                val searchBlog2 = Session["searchblog"] as String
                Log.e("Dữ Liệu SearchBlog2: ","$searchBlog2")
                supportFragmentManager.beginTransaction().apply {
                    replace(R.id.home_search_blog,searchDetailBlog)
                    commit()
                }

            }else{
                Log.e("Back: " ,"Home Search Blog")
                supportFragmentManager.beginTransaction().apply {
                    replace(R.id.home_search_blog,homeSearchBlog)
                    commit()
                }
            }
        }

        setContentView(binding.root)
    }
}