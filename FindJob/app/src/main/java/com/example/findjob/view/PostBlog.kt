package com.example.findjob.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.navigation.fragment.findNavController
import com.example.findjob.R
import com.example.findjob.controller.CustomSpinnerAdapter
import com.example.findjob.databinding.FragmentPostBlogBinding

class PostBlog : Fragment() {
    lateinit var binding: FragmentPostBlogBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPostBlogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnBackPostBlog.setOnClickListener {
            findNavController().navigate(R.id.action_postBlog_to_manageBlog)
        }
        blog_spiner()
    }

    private fun blog_spiner() {
        val list = resources.getStringArray(R.array.sp_Blog)
        val adtt = activity?.let{ArrayAdapter(it,R.layout.layout_txt_sp, list)}
//        val adapterr = activity?.let{CustomSpinnerAdapter(this,list.toList())}
        //gọi spiner
        binding.spBlog.adapter = adtt
    }

    companion object {}
}