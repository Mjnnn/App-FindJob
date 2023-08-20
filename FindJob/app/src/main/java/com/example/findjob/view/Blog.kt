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
import com.example.findjob.controller.*
import com.example.findjob.databinding.FragmentBlogBinding
import com.example.findjob.model.Blog
import com.example.findjob.model.DataView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Blog : Fragment() {
    lateinit var binding:FragmentBlogBinding
    var dsTopicKNPV = mutableListOf<DataView>()
    var dsTopicHN = mutableListOf<DataView>()
    var dsTopicGCG = mutableListOf<DataView>()
    var dsTopicBQTV = mutableListOf<DataView>()
    var dsTopicMCS = mutableListOf<DataView>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.txtBlogSearch.setOnClickListener {
            var i = Intent(activity,SearchBlog::class.java)
            startActivity(i)
        }

        getDataTopicKinhNghiemPhongVan()
        getDataTopicHuongNghiep()
        getDataGocChuyenGia()
        getDataBiQuyetTimViec()
        getDataMeoCongSo()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentBlogBinding.inflate(inflater, container, false)
//        return inflater.inflate(R.layout.fragment_blog, container, false)
        return  binding.root
    }

    private fun getDataTopicKinhNghiemPhongVan() {
        ApiSever.retrofit.getTopicBlog("Kinh nghiệm phỏng vấn").enqueue(
            object: Callback<List<Blog>> {
                override fun onResponse(call: Call<List<Blog>>, response: Response<List<Blog>>) {
                    var blog = response.body()
                    Log.e("Topic Kinh Nghiệm Phỏng Vấn Thành Công: ","Thành Công")
                    var arr:Int = blog?.size as Int
                    for (i in 0 .. arr-1){
                        dsTopicKNPV.add(
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
                    val adapterr = ViewAdapterTopicBlog(dsTopicKNPV,object : RvInterface {
                        override fun OnClickRv(pos: Int) {
                            Toast.makeText(activity,"Bạn vừa click vào ${dsTopicKNPV[pos].titleblog}", Toast.LENGTH_LONG).show()
                            val i = Intent(activity,BlogDetail::class.java)
                            i.putExtra("idblog",dsTopicKNPV[pos].idblog)
                            i.putExtra("usernameblog",dsTopicKNPV[pos].username)
                            i.putExtra("titleblog",dsTopicKNPV[pos].titleblog)
                            i.putExtra("contentblog",dsTopicKNPV[pos].contentblog)
                            i.putExtra("timeblog",dsTopicKNPV[pos].timeblog)
                            i.putExtra("topicblog",dsTopicKNPV[pos].topicblog)
                            startActivity(i)
                        }
                    })

                    binding.rvKinhnghiemphongvanBlog.adapter = adapterr
                    binding.rvKinhnghiemphongvanBlog.layoutManager = LinearLayoutManager(activity,
                        LinearLayoutManager.VERTICAL,false)
                }

                override fun onFailure(call: Call<List<Blog>>, t: Throwable) {
                    Log.e("Topic Kinh Nghiệm Phỏng Vấn Thất Bại: ","${t}")
                }
            }
        )
    }

    private fun getDataTopicHuongNghiep() {
        ApiSever.retrofit.getTopicBlog("Hướng Nghiệp").enqueue(
            object: Callback<List<Blog>> {
                override fun onResponse(call: Call<List<Blog>>, response: Response<List<Blog>>) {
                    var blog = response.body()
                    Log.e("Topic Hướng Nghiệp Thành Công: ","Thành Công")
                    var arr:Int = blog?.size as Int
                    for (i in 0 .. arr-1){
                        dsTopicHN.add(
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
                    val adapterr = ViewAdapterTopicBlogHorizontal(dsTopicHN,object : RvInterface {
                        override fun OnClickRv(pos: Int) {
                            Toast.makeText(activity,"Bạn vừa click vào ${dsTopicHN[pos].titleblog}", Toast.LENGTH_LONG).show()
                            val i = Intent(activity,BlogDetail::class.java)
                            i.putExtra("idblog",dsTopicHN[pos].idblog)
                            i.putExtra("usernameblog",dsTopicHN[pos].username)
                            i.putExtra("titleblog",dsTopicHN[pos].titleblog)
                            i.putExtra("contentblog",dsTopicHN[pos].contentblog)
                            i.putExtra("timeblog",dsTopicHN[pos].timeblog)
                            i.putExtra("topicblog",dsTopicHN[pos].topicblog)
                            startActivity(i)

                        }
                    })

                    binding.rvHuongnghiepBlog.adapter = adapterr
                    binding.rvHuongnghiepBlog.layoutManager = LinearLayoutManager(activity,
                        LinearLayoutManager.HORIZONTAL,false)
                }

                override fun onFailure(call: Call<List<Blog>>, t: Throwable) {
                    Log.e("Topic Hướng Nghiệp Thất Bại: ","${t}")
                }
            }
        )
    }

    private fun getDataGocChuyenGia() {
        ApiSever.retrofit.getTopicBlog("Góc Chuyên Gia").enqueue(
            object: Callback<List<Blog>> {
                override fun onResponse(call: Call<List<Blog>>, response: Response<List<Blog>>) {
                    var blog = response.body()
                    Log.e("Topic Góc Chuyên Gia Thành Công: ","Thành Công")
                    var arr:Int = blog?.size as Int
                    for (i in 0 .. arr-1){
                        dsTopicGCG.add(
                            DataView(
                                blog[i].idblog,
                                blog[i].username,
                                blog[i].titleblog,
                                blog[i].contentblog,
                                R.drawable.img_demo_blog_3,
                                blog[i].timeblog,
                                blog[i].topicblog,
                                blog[i].trangthai
                            )
                        )

                    }
                    val adapterr = ViewAdapterTopicBlog(dsTopicGCG,object : RvInterface {
                        override fun OnClickRv(pos: Int) {
                            Toast.makeText(activity,"Bạn vừa click vào ${dsTopicGCG[pos].titleblog}", Toast.LENGTH_LONG).show()
                            val i = Intent(activity,BlogDetail::class.java)
                            i.putExtra("idblog",dsTopicGCG[pos].idblog)
                            i.putExtra("usernameblog",dsTopicGCG[pos].username)
                            i.putExtra("titleblog",dsTopicGCG[pos].titleblog)
                            i.putExtra("contentblog",dsTopicGCG[pos].contentblog)
                            i.putExtra("timeblog",dsTopicGCG[pos].timeblog)
                            i.putExtra("topicblog",dsTopicGCG[pos].topicblog)
                            startActivity(i)
                        }
                    })

                    binding.rvGocchuyengiaBlog.adapter = adapterr
                    binding.rvGocchuyengiaBlog.layoutManager = LinearLayoutManager(activity,
                        LinearLayoutManager.VERTICAL,false)
                }

                override fun onFailure(call: Call<List<Blog>>, t: Throwable) {
                    Log.e("Topic Góc Chuyên Gia Thất Bại: ","${t}")
                }
            }
        )
    }

    private fun getDataBiQuyetTimViec() {
        ApiSever.retrofit.getTopicBlog("Bí Quyết Tìm Việc").enqueue(
            object: Callback<List<Blog>> {
                override fun onResponse(call: Call<List<Blog>>, response: Response<List<Blog>>) {
                    var blog = response.body()
                    Log.e("Topic Bí Quyết Tìm Việc Thành Công: ","Thành Công")
                    var arr:Int = blog?.size as Int
                    for (i in 0 .. arr-1){
                        dsTopicBQTV.add(
                            DataView(
                                blog[i].idblog,
                                blog[i].username,
                                blog[i].titleblog,
                                blog[i].contentblog,
                                R.drawable.img_demo_blog_4,
                                blog[i].timeblog,
                                blog[i].topicblog,
                                blog[i].trangthai
                            )
                        )

                    }
                    val adapterr = ViewAdapterTopicBlogHorizontal(dsTopicBQTV,object : RvInterface {
                        override fun OnClickRv(pos: Int) {
                            Toast.makeText(activity,"Bạn vừa click vào ${dsTopicBQTV[pos].titleblog}", Toast.LENGTH_LONG).show()
                            val i = Intent(activity,BlogDetail::class.java)
                            i.putExtra("idblog",dsTopicBQTV[pos].idblog)
                            i.putExtra("usernameblog",dsTopicBQTV[pos].username)
                            i.putExtra("titleblog",dsTopicBQTV[pos].titleblog)
                            i.putExtra("contentblog",dsTopicBQTV[pos].contentblog)
                            i.putExtra("timeblog",dsTopicBQTV[pos].timeblog)
                            i.putExtra("topicblog",dsTopicBQTV[pos].topicblog)
                            startActivity(i)
                        }
                    })

                    binding.rvBiquyettimviecBlog.adapter = adapterr
                    binding.rvBiquyettimviecBlog.layoutManager = LinearLayoutManager(activity,
                        LinearLayoutManager.HORIZONTAL,false)
                }

                override fun onFailure(call: Call<List<Blog>>, t: Throwable) {
                    Log.e("Topic Bí Quyết Tìm Việc Thất Bại: ","${t}")
                }
            }
        )
    }

    private fun getDataMeoCongSo() {
        ApiSever.retrofit.getTopicBlog("Mẹo Công Sở").enqueue(
            object: Callback<List<Blog>> {
                override fun onResponse(call: Call<List<Blog>>, response: Response<List<Blog>>) {
                    var blog = response.body()
                    Log.e("Topic Mẹo Công Sở Thành Công: ","Thành Công")
                    var arr:Int = blog?.size as Int
                    for (i in 0 .. arr-1){
                        dsTopicMCS.add(
                            DataView(
                                blog[i].idblog,
                                blog[i].username,
                                blog[i].titleblog,
                                blog[i].contentblog,
                                R.drawable.img_demo_blog_5,
                                blog[i].timeblog,
                                blog[i].topicblog,
                                blog[i].trangthai
                            )
                        )

                    }
                    val adapterr = ViewAdapterTopicBlog(dsTopicMCS,object : RvInterface {
                        override fun OnClickRv(pos: Int) {
                            Toast.makeText(activity,"Bạn vừa click vào ${dsTopicMCS[pos].titleblog}", Toast.LENGTH_LONG).show()
                            val i = Intent(activity,BlogDetail::class.java)
                            i.putExtra("idblog",dsTopicMCS[pos].idblog)
                            i.putExtra("usernameblog",dsTopicMCS[pos].username)
                            i.putExtra("titleblog",dsTopicMCS[pos].titleblog)
                            i.putExtra("contentblog",dsTopicMCS[pos].contentblog)
                            i.putExtra("timeblog",dsTopicMCS[pos].timeblog)
                            i.putExtra("topicblog",dsTopicMCS[pos].topicblog)
                            startActivity(i)
                        }
                    })

                    binding.rvMeocongsoBlog.adapter = adapterr
                    binding.rvMeocongsoBlog.layoutManager = LinearLayoutManager(activity,
                        LinearLayoutManager.VERTICAL,false)
                }

                override fun onFailure(call: Call<List<Blog>>, t: Throwable) {
                    Log.e("Topic Mẹo Công Sở Thất Bại: ","${t}")
                }
            }
        )
    }




    companion object {

    }
}