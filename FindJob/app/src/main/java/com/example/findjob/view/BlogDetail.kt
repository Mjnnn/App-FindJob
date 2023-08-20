package com.example.findjob.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.findjob.R
import com.example.findjob.controller.*
import com.example.findjob.databinding.ActivityBlogDetailBinding
import com.example.findjob.model.Blog
import com.example.findjob.model.DataView
import com.example.findjob.model.RatingBlog
import com.example.findjob.model.RatingJob
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BlogDetail : AppCompatActivity() {
    lateinit var binding:ActivityBlogDetailBinding
    var dsBlog = mutableListOf<DataView>()
    var dsRt = mutableListOf<DataView>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBlogDetailBinding.inflate(layoutInflater)
        val i = intent
        var idblog = i.getIntExtra("idblog",0)
        var username = i.getStringExtra("usernameblog")
        var titleblog = i.getStringExtra("titleblog")
        var contentblog = i.getStringExtra("contentblog")
        var timeblog = i.getStringExtra("timeblog")
        var topicblog = i.getStringExtra("topicblog") as String

        binding.txtTitleBlogDetail.text = titleblog
        binding.txtContentBlogDetail.text = contentblog
        binding.txtUsernameBlogDetail.text = "Người đăng tải: $username"
        binding.txtTimeBlogDetail.text = "Vào lúc: $timeblog"

        getDataSameTopicBlog(topicblog)
        getRatingBlog(idblog)
        setContentView(binding.root)
    }

    private fun getRatingBlog(idblog: Int) {
        ApiSever.retrofit.getRatingBlog(idblog).enqueue(
            object: Callback<List<RatingBlog>>{
                override fun onResponse(
                    call: Call<List<RatingBlog>>,
                    response: Response<List<RatingBlog>>
                ) {
                    Log.e("TestGetData RatingJobDetail: ","Thành Công")
                    var rating = response.body()
                    var arr:Int = rating?.size as Int
                    if(arr>0){
                        for (i in 0 .. arr-1){
                            dsRt.add(
                                DataView(
                                    rating[i].idcmt,
                                    rating[i].username,
                                    rating[i].idblog,
                                    rating[i].content,
                                    rating[i].ratingblog,
                                    R.drawable.tlen2,
                                    rating[i].time,
                                )
                            )
                        }
                        val adapterr = ViewAdapterRating(dsRt,object : RvInterface{
                            override fun OnClickRv(pos: Int) {

                            }
                        })
                        binding.rvCmtBlogDetail.adapter = adapterr
                        binding.rvCmtBlogDetail.layoutManager = LinearLayoutManager(this@BlogDetail,
                            LinearLayoutManager.VERTICAL,false)
                    }else{
                        Log.e("TestGetData RatingBlogDetail Thành Công: ","Không có phản hồi nào!")
                    }
                }

                override fun onFailure(call: Call<List<RatingBlog>>, t: Throwable) {
                    Log.e("TestGetData RatingBlogDetail Thất Bại: ","${t}")
                }
            }
        )
    }

    private fun getDataSameTopicBlog(topicblog: String) {
        ApiSever.retrofit.getTopicBlog(topicblog).enqueue(
            object: Callback<List<Blog>> {
                override fun onResponse(call: Call<List<Blog>>, response: Response<List<Blog>>) {
                    var blog = response.body()
                    Log.e("Same Topic Thành Công: ","Thành Công")
                    var arr:Int = blog?.size as Int
                    for (i in 0 .. arr-1){
                        dsBlog.add(
                            DataView(
                                blog[i].idblog,
                                blog[i].username,
                                blog[i].titleblog,
                                blog[i].contentblog,
                                R.drawable.img_demo_topic_blog_2,
                                blog[i].timeblog,
                                blog[i].topicblog,
                                blog[i].trangthai
                            )
                        )

                    }
                    val adapterr = ViewAdapterSameTopicBlog(dsBlog,object : RvInterface {
                        override fun OnClickRv(pos: Int) {
                            Toast.makeText(this@BlogDetail,"Bạn vừa click vào ${dsBlog[pos].titleblog}", Toast.LENGTH_LONG).show()
                            val i = Intent(this@BlogDetail,BlogDetail::class.java)
                            i.putExtra("idblog",dsBlog[pos].idblog)
                            i.putExtra("usernameblog",dsBlog[pos].username)
                            i.putExtra("titleblog",dsBlog[pos].titleblog)
                            i.putExtra("contentblog",dsBlog[pos].contentblog)
                            i.putExtra("timeblog",dsBlog[pos].timeblog)
                            i.putExtra("topicblog",dsBlog[pos].topicblog)
                            startActivity(i)
                        }
                    })

                    binding.rvBlogLienquan.adapter = adapterr
                    binding.rvBlogLienquan.layoutManager = LinearLayoutManager(this@BlogDetail,
                        LinearLayoutManager.HORIZONTAL,false)
                }

                override fun onFailure(call: Call<List<Blog>>, t: Throwable) {
                    Log.e("Same Topic Thất Bại: ","${t}")
                }
            }
        )
    }
}