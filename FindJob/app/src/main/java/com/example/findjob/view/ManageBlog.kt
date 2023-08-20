package com.example.findjob.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.findjob.R
import com.example.findjob.controller.*
import com.example.findjob.databinding.FragmentManageBlogBinding
import com.example.findjob.model.Blog
import com.example.findjob.model.DataView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ManageBlog : Fragment() {
    lateinit var binding : FragmentManageBlogBinding
    var dsBlog = mutableListOf<DataView>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentManageBlogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnAddBlog.setOnClickListener {
            findNavController().navigate(R.id.action_manageBlog_to_postBlog)
        }
        binding.btnBackBlog.setOnClickListener {
            findNavController().navigate(R.id.action_manageBlog_to_profileDetail)
        }
        val user = Session["username"] as String
        if(user != ""){
            ApiSever.retrofit.getListBlog(user).enqueue(
                object : Callback<List<com.example.findjob.model.Blog>>{
                    override fun onResponse(
                        call: Call<List<Blog>>,
                        response: Response<List<Blog>>
                    ) {
                        Log.e("List Blog Thành Công: ","Thành Công")
                        var blog = response.body()
                        var arr:Int = blog?.size as Int
                        if(arr > 0 ){
                            binding.txtErrorListBlog.visibility = View.GONE
                            for (i in 0 .. arr-1){
                                dsBlog.add(DataView(
                                    blog[i].idblog,
                                    blog[i].username,
                                    blog[i].titleblog,
                                    blog[i].contentblog,
                                    R.drawable.img_blog,
                                    blog[i].timeblog,
                                    blog[i].topicblog,
                                    blog[i].trangthai
                                ))

                            }
                            val adapterr = ViewAdapterListBlog(dsBlog,object : RvInterface {
                                override fun OnClickRv(pos: Int) {
                                    Toast.makeText(activity,"Bạn vừa click vào ${dsBlog[pos].titleblog}", Toast.LENGTH_LONG).show()
                                    val i = Intent(activity,BlogDetail::class.java)
                                    i.putExtra("idblog",dsBlog[pos].idblog)
                                    i.putExtra("usernameblog",dsBlog[pos].username)
                                    i.putExtra("titleblog",dsBlog[pos].titleblog)
                                    i.putExtra("contentblog",dsBlog[pos].contentblog)
                                    i.putExtra("timeblog",dsBlog[pos].timeblog)
                                    i.putExtra("topicblog",dsBlog[pos].topicblog)
                                    startActivity(i)
                                }
                            })

                            binding.rvListBlog.adapter = adapterr
                            binding.rvListBlog.layoutManager = LinearLayoutManager(activity,
                                LinearLayoutManager.VERTICAL,false)
                        }
                    }

                    override fun onFailure(call: Call<List<Blog>>, t: Throwable) {
                        Log.e("List Blog Thất Bại: ","$t")
                    }
                }
            )
        }
    }

    companion object {}
}