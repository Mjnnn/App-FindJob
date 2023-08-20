package com.example.findjob.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.findjob.R
import com.example.findjob.controller.ApiSever
import com.example.findjob.controller.RvInterface
import com.example.findjob.controller.Session
import com.example.findjob.controller.ViewAdapterTopicBlog
import com.example.findjob.databinding.FragmentSearchDetailBlogBinding
import com.example.findjob.model.Blog
import com.example.findjob.model.DataView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SearchDetailBlog : Fragment() {
    var dsSearchBlog = mutableListOf<DataView>()
    lateinit var binding:FragmentSearchDetailBlogBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
            val searchBlog = Session["searchblog"] as String
            binding.txtErrorSearchDetailBlog.visibility = View.GONE
        if (searchBlog != ""){
            getDataSearchBlog(searchBlog)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchDetailBlogBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun getDataSearchBlog(searchBlog: String) {
        ApiSever.retrofit.getSearchDetailBlog(searchBlog).enqueue(
            object: Callback<List<Blog>> {
                override fun onResponse(call: Call<List<Blog>>, response: Response<List<Blog>>) {
                    var blog = response.body()
                    Log.e("Search Detail Blog: ","Thành Công")
                    var arr:Int = blog?.size as Int
                    if(arr > 0){
                        for (i in 0 .. arr-1){
                            dsSearchBlog.add(
                                DataView(
                                    blog[i].idblog,
                                    blog[i].username,
                                    blog[i].titleblog,
                                    blog[i].contentblog,
                                    R.drawable.img_topic_blog,
                                    blog[i].timeblog,
                                    blog[i].topicblog,
                                    blog[i].trangthai
                                )
                            )

                        }
                        val adapterr = ViewAdapterTopicBlog(dsSearchBlog,object : RvInterface {
                            override fun OnClickRv(pos: Int) {
                                Toast.makeText(activity,"Bạn vừa click vào ${dsSearchBlog[pos].titleblog}", Toast.LENGTH_LONG).show()
                                val i = Intent(activity,BlogDetail::class.java)
                                i.putExtra("idblog",dsSearchBlog[pos].idblog)
                                i.putExtra("usernameblog",dsSearchBlog[pos].username)
                                i.putExtra("titleblog",dsSearchBlog[pos].titleblog)
                                i.putExtra("contentblog",dsSearchBlog[pos].contentblog)
                                i.putExtra("timeblog",dsSearchBlog[pos].timeblog)
                                i.putExtra("topicblog",dsSearchBlog[pos].topicblog)
                                startActivity(i)
                            }
                        })
                        binding.txtResultSearchDetailBlog.text = "Kết quả tìm kiếm: $arr"
                        binding.rvSearchDetailBlog.adapter = adapterr
                        binding.rvSearchDetailBlog.layoutManager = LinearLayoutManager(activity,
                            LinearLayoutManager.VERTICAL,false)
                    }else{
                        binding.txtResultSearchDetailBlog.text = "Kết quả tìm kiếm: 0"
                        binding.txtErrorSearchDetailBlog.visibility = View.VISIBLE
                    }
                }

                override fun onFailure(call: Call<List<Blog>>, t: Throwable) {
                    Log.e("Search Detail Thất Bại: ","${t}")
                }
            }
        )
    }

    companion object {}
}