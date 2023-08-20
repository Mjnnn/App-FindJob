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
import com.example.findjob.databinding.FragmentManageFollowBinding
import com.example.findjob.model.Blog
import com.example.findjob.model.Company
import com.example.findjob.model.DataView
import com.example.findjob.model.Hired
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ManageFollow : Fragment() {
        lateinit var binding: FragmentManageFollowBinding
        var dsHired = mutableListOf<DataView>()
        var dsCompany = mutableListOf<DataView>()
        var dsBlog = mutableListOf<DataView>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentManageFollowBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnBackFollow.setOnClickListener {
            findNavController().navigate(R.id.action_manageFollow_to_profileDetail)
        }
        val user = Session["username"] as String
        if(user != ""){
            getDataFollowJob(user)
            getDataFollowCompany(user)
            getDataFollowBlog(user)
        }
    }

    private fun getDataFollowBlog(user: String) {
        ApiSever.retrofit.getListBlog(user).enqueue(
            object : Callback<List<com.example.findjob.model.Blog>>{
                override fun onResponse(
                    call: Call<List<Blog>>,
                    response: Response<List<Blog>>
                ) {
                    Log.e("Follow Blog Thành Công: ","Thành Công")
                    var blog = response.body()
                    var arr:Int = blog?.size as Int
                    if(arr > 0 ){
                        binding.txtErrorListFollowBlog.visibility = View.GONE
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

                        binding.rvListFollowBlog.adapter = adapterr
                        binding.rvListFollowBlog.layoutManager = LinearLayoutManager(activity,
                            LinearLayoutManager.VERTICAL,false)
                    }
                }

                override fun onFailure(call: Call<List<Blog>>, t: Throwable) {
                    Log.e("Follow Blog Thất Bại: ","$t")
                }
            }
        )
    }

    private fun getDataFollowCompany(user: String) {
        ApiSever.retrofit.getFollowCompany(user).enqueue(
            object : Callback<List<Company>>{
                override fun onResponse(
                    call: Call<List<Company>>,
                    response: Response<List<Company>>
                ) {
                    var company = response.body()
                    Log.e("Follow Company Thành Công: ","Thành Công")
                    var arr:Int = company?.size as Int
                    Log.e("Size Arr List Company: ","$arr")
                    if(arr>0){
                        binding.txtErrorListFollowCompany.visibility = View.GONE
                        for (i in 0.. arr-1){
                            dsCompany.add(
                                DataView(
                                    company[i].idcongty,
                                    company[i].username,
                                    company[i].tencongty,
                                    company[i].loaihinhhd,
                                    company[i].trangthaicty,
                                    company[i].sonv,
                                    company[i].soluoccty,
                                    company[i].quocgia,
                                    company[i].tinh_tp,
                                    company[i].diachi,
                                    company[i].tennguoilh,
                                    company[i].sdt,
                                    company[i].email,
                                    R.drawable.img_company,
                                    R.drawable.img_demo_company,
                                    company[i].linkwebsite,
                                    company[i].phucloicty,
                                    company[i].masothue,
                                    company[i].trangthaichitiet,
                                    company[i].ngaydangky
                                )
                            )
                        }
                        val adapterr = ViewAdapterListCompany(dsCompany,object : RvInterface {
                            override fun OnClickRv(pos: Int) {
                                Toast.makeText(activity,"Bạn vừa click vào ${dsCompany[pos].tencongty}", Toast.LENGTH_LONG).show()
                                val i = Intent(activity,CompanyDetail::class.java)
                                i.putExtra("idcongty",dsCompany[pos].idcongty)
                                i.putExtra("username",dsCompany[pos].username)
                                i.putExtra("tencongty",dsCompany[pos].tencongty)
                                i.putExtra("diachi",dsCompany[pos].diaChi)
                                i.putExtra("tinh_tp",dsCompany[pos].tinh_tp)
                                i.putExtra("trangthaicty",dsCompany[pos].trangthaicty)
                                i.putExtra("loaihinhhd",dsCompany[pos].loaihinhhd)
                                i.putExtra("sonv",dsCompany[pos].sonv)
                                i.putExtra("soluoccty",dsCompany[pos].soluoccty)
                                i.putExtra("tennguoilh",dsCompany[pos].tennguoilh)
                                i.putExtra("sdt",dsCompany[pos].sdt)
                                i.putExtra("email",dsCompany[pos].email)
                                startActivity(i)
                            }
                        })

                        binding.rvListFollowCompany.adapter = adapterr
                        binding.rvListFollowCompany.layoutManager = LinearLayoutManager(activity,
                            LinearLayoutManager.VERTICAL,false)
                    }else{

                    }
                }

                override fun onFailure(call: Call<List<Company>>, t: Throwable) {
                    Log.e("Follow Company Thất Bại: ","$t")
                }
            }
        )
    }

    private fun getDataFollowJob(user: String) {
        ApiSever.retrofit.getFollowJob(user).enqueue(
            object: Callback<List<Hired>> {
                override fun onResponse(
                    call: Call<List<Hired>>,
                    response: Response<List<Hired>>
                ) {
                    Log.e("Get Data Follow Job Thành Công: ","Thành Công")
                    var account = response.body()
                    var arr:Int = account?.size as Int
                    Log.e("Size Arr Job: ","$arr")
                    if(arr > 0 ){
                        binding.txtErrorListFollowJob.visibility = View.GONE
                        for (i in 0 .. arr-1){
//                            var x:String = account[i].imgcongviec
                            var diachi:String = "${account[i].diachi},${account[i].tinh_tp}"
                            dsHired.add(
                                DataView(
                                    account[i].idcongviec,
                                    account[i].idcongty,
                                    account[i].username,
                                    R.drawable.img_job,
                                    account[i].nganhnghe,
                                    account[i].theloai,
                                    account[i].chucdanh,
                                    account[i].soluong,
                                    account[i].mucluong,diachi,
                                    account[i].luyy,
                                    account[i].ngayketthuc)
                            )
                        }
                        val adapterr = ViewAdapterListJob(dsHired,object : RvInterface {
                            override fun OnClickRv(pos: Int) {
                                Toast.makeText(activity,"Bạn vừa click vào ${dsHired[pos].tenCongViec}", Toast.LENGTH_LONG).show()
                                val i = Intent(activity,JobDetail::class.java)
                                i.putExtra("idcongviec",dsHired[pos].idcongviec)
                                i.putExtra("tencongviec","${dsHired[pos].tenCongViec}")
                                i.putExtra("vitri","${dsHired[pos].chucdanh}")
                                i.putExtra("diachi","${dsHired[pos].diaChi}")
                                i.putExtra("theloai","${dsHired[pos].theLoai}")
                                i.putExtra("luyy","${dsHired[pos].luyy}")
                                i.putExtra("mucluong","${dsHired[pos].mucluong}")
                                i.putExtra("soluong",dsHired[pos].soLuong)
                                Log.e("Số Lượng: ","${dsHired[pos].soLuong}")
                                i.putExtra("ngayketthuc","${dsHired[pos].ngayketthuc}")
                                startActivity(i)
                            }
                        })

                        binding.rvListFollowJob.adapter = adapterr
                        binding.rvListFollowJob.layoutManager = LinearLayoutManager(activity,
                            LinearLayoutManager.VERTICAL,false)
                    }



                }

                override fun onFailure(call: Call<List<Hired>>, t: Throwable) {
                    Log.e("Get Data Follow Job Thất Bại: ","${t}")
                }
            }

        )
    }

    companion object {}
}