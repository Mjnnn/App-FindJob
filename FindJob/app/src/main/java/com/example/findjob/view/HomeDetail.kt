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
import com.example.findjob.databinding.FragmentHomeDetailBinding
import com.example.findjob.model.Company
import com.example.findjob.model.DataView
import com.example.findjob.model.Hired
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeDetail : Fragment() {
    var dsHired = mutableListOf<DataView>()
    var dsHiredIt = mutableListOf<DataView>()
    var dsBlog = mutableListOf<DataView>()
    var dsCompany = mutableListOf<DataView>()
    lateinit var binding: FragmentHomeDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeDetailBinding.inflate(inflater, container, false)
//        return inflater.inflate(R.layout.fragment_home_detail, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.txtHomeSearch.setOnClickListener {
            findNavController().navigate(R.id.action_homeDetail_to_searchJob)
        }

        // Rv_Hired
        var titlejobb:String = ""
        if( Session["titlejob"] as String != null ){
           titlejobb = Session["titlejob"] as String
        }

        var locationn:String =""
        if (Session["location"] as String != null){
            locationn = Session["location"] as String
        }

        if(titlejobb != "" && locationn != ""){
            getDataHired_titlejob_location(titlejobb,locationn)
        }else if(titlejobb != ""){
            getDataHired_titlejob(titlejobb)
        }else if(locationn != ""){
            getDataHired_location(locationn)
        }else{
            getDataHired()
        }

        // Rv_Hired_IT
        getDataHiredIt()

        // Rv_Blog
        getDataBlog()

        // Rv_Company
        getDataCompany()








    }

    private fun getDataCompany() {
        ApiSever.retrofit.getCompany().enqueue(
            object : Callback<List<Company>>{
                override fun onResponse(
                    call: Call<List<Company>>,
                    response: Response<List<Company>>
                ) {
                    var company = response.body()
                    Log.e("TestGetData Company Thành Công: ","Thành Công")
                    var arr:Int = company?.size as Int
                    Log.e("Size Arr Company: ","$arr")
                    if(arr>0){
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
                                R.drawable.img_demo_company,
                                R.drawable.img_demo_company,
                                company[i].linkwebsite,
                                company[i].phucloicty,
                                company[i].masothue,
                                company[i].trangthaichitiet,
                                company[i].ngaydangky
                            )
                            )
                        }
                        val adapterr = ViewAdapterCompanyHomeDetail(dsCompany,object : RvInterface {
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

                        binding.rvCompanyHomeDetail.adapter = adapterr
                        binding.rvCompanyHomeDetail.layoutManager = LinearLayoutManager(activity,
                        LinearLayoutManager.HORIZONTAL,false)
                    }
                }

                override fun onFailure(call: Call<List<Company>>, t: Throwable) {
                    Log.e("TestGetData Company Thất Bại: ","${t}")
                }
            }
        )
    }

    private fun getDataBlog() {
        ApiSever.retrofit.getBlog().enqueue(
            object: Callback<List<com.example.findjob.model.Blog>> {
                override fun onResponse(call: Call<List<com.example.findjob.model.Blog>>, response: Response<List<com.example.findjob.model.Blog>>) {
                    var blog = response.body()
                    Log.e("TestGetData Blog Thành Công: ","Thành Công")
                    var arr:Int = blog?.size as Int
                    for (i in 0 .. arr-1){
                        dsBlog.add(DataView(
                            blog[i].idblog,
                            blog[i].username,
                            blog[i].titleblog,
                            blog[i].contentblog,
                            R.drawable.img_demo_blog,
                            blog[i].timeblog,
                            blog[i].topicblog,
                            blog[i].trangthai
                        ))

                    }
                    val adapterr = ViewAdapterBlogHomeDetail(dsBlog,object : RvInterface {
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

                    binding.rvBlogHomeDetail.adapter = adapterr
                    binding.rvBlogHomeDetail.layoutManager = LinearLayoutManager(activity,
                        LinearLayoutManager.VERTICAL,false)
                }

                override fun onFailure(call: Call<List<com.example.findjob.model.Blog>>, t: Throwable) {
                    Log.e("TestGetData Blog Thất Bại: ","${t}")
                }
            }
        )
    }

    private fun getDataHiredIt() {
        ApiSever.retrofit.getHired_titlejob("công nghệ thông tin").enqueue(
            object: Callback<List<Hired>> {
                override fun onResponse(
                    call: Call<List<Hired>>,
                    response: Response<List<Hired>>
                ) {
                    Log.e("TestGetData Hired_IT Thành Công: ","Thành Công")
                    var account = response.body()
                    var arr:Int = account?.size as Int
                    Log.e("Size Arr Hired_IT: ","$arr")
                    for (i in 0 .. arr-1){
//                            var x:String = account[i].imgcongviec
                        var diachi:String = "${account[i].diachi},${account[i].tinh_tp}"
                        Log.e("Địa Chỉ Hired_IT: ","$diachi")
                        dsHiredIt.add(DataView(
                            account[i].idcongviec,
                            account[i].idcongty,
                            account[i].username,
                            R.drawable.img_demo_hired_it,
                            account[i].nganhnghe,
                            account[i].theloai,
                            account[i].chucdanh,
                            account[i].soluong,
                            account[i].mucluong,diachi,
                            account[i].luyy,
                            account[i].ngayketthuc))
                    }
                    val adapterr = ViewAdapterHirerITHomeDetail(dsHiredIt,object : RvInterface {
                        override fun OnClickRv(pos: Int) {
                            Toast.makeText(activity,"Bạn vừa click vào ${dsHiredIt[pos].tenCongViec}", Toast.LENGTH_LONG).show()
                            val i = Intent(activity,JobDetail::class.java)
                            i.putExtra("idcongviec",dsHiredIt[pos].idcongviec)
                            i.putExtra("tencongviec","${dsHiredIt[pos].tenCongViec}")
                            i.putExtra("vitri","${dsHiredIt[pos].chucdanh}")
                            i.putExtra("diachi","${dsHiredIt[pos].diaChi}")
                            i.putExtra("theloai","${dsHiredIt[pos].theLoai}")
                            i.putExtra("luyy","${dsHiredIt[pos].luyy}")
                            i.putExtra("mucluong","${dsHiredIt[pos].mucluong}")
                            i.putExtra("soluong",dsHiredIt[pos].soLuong)
                            Log.e("Số Lượng: ","${dsHiredIt[pos].soLuong}")
                            i.putExtra("ngayketthuc","${dsHiredIt[pos].ngayketthuc}")
                            startActivity(i)
                        }
                    })

                    binding.rvHiredItHomeDetail.adapter = adapterr
                    binding.rvHiredItHomeDetail.layoutManager = LinearLayoutManager(activity,
                        LinearLayoutManager.HORIZONTAL,false)


                }

                override fun onFailure(call: Call<List<Hired>>, t: Throwable) {
                    Log.e("TestGetData Hired_IT Thất Bại: ","${t}")
                }
            }

        )
    }

    private fun getDataHired() {
        ApiSever.retrofit.getHired().enqueue(
            object: Callback<List<Hired>> {
                override fun onResponse(
                    call: Call<List<Hired>>,
                    response: Response<List<Hired>>
                ) {
                    Log.e("TestGetData Hired Thành Công: ","Thành Công")
                    var account = response.body()
                    var arr:Int = account?.size as Int
                    Log.e("Size Arr Hired: ","$arr")
                    for (i in 0 .. arr-1){
//                            var x:String = account[i].imgcongviec
                        var diachi:String = "${account[i].diachi},${account[i].tinh_tp}"
                        Log.e("Địa Chỉ: ","$diachi")
                        dsHired.add(DataView(
                            account[i].idcongviec,
                            account[i].idcongty,
                            account[i].username,
                            R.drawable.img_demo_hired,
                            account[i].nganhnghe,
                            account[i].theloai,
                            account[i].chucdanh,
                            account[i].soluong,
                            account[i].mucluong,diachi,
                            account[i].luyy,
                            account[i].ngayketthuc))
                    }
                    val adapterr = ViewAdapterHiredHomeDetail(dsHired,object : RvInterface {
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

                    binding.rvHiredHomeDetail.adapter = adapterr
                    binding.rvHiredHomeDetail.layoutManager = LinearLayoutManager(activity,
                        LinearLayoutManager.HORIZONTAL,false)


                }

                override fun onFailure(call: Call<List<Hired>>, t: Throwable) {
                    Log.e("TestGetData Hired Thất Bại: ","${t}")
                }
            }

        )
    }

    private fun getDataHired_location(locationn: String) {
        ApiSever.retrofit.getHired_location(locationn).enqueue(
            object: Callback<List<Hired>> {
                override fun onResponse(
                    call: Call<List<Hired>>,
                    response: Response<List<Hired>>
                ) {
                    Log.e("TestGetData Hired_Location Thành Công: ","Thành Công")
                    var account = response.body()
                    var arr:Int = account?.size as Int
                    Log.e("Size Arr Hired_Location: ","$arr")
                    if(arr > 0 ){
                        for (i in 0 .. arr-1){
//                            var x:String = account[i].imgcongviec
                            var diachi:String = "${account[i].diachi},${account[i].tinh_tp}"
                            Log.e("Địa Chỉ: ","$diachi")
                            dsHired.add(DataView(
                                account[i].idcongviec,
                                account[i].idcongty,
                                account[i].username,
                                R.drawable.img_demo_hired,
                                account[i].nganhnghe,
                                account[i].theloai,
                                account[i].chucdanh,
                                account[i].soluong,
                                account[i].mucluong,
                                diachi,
                                account[i].luyy,
                                account[i].ngayketthuc))
                        }
                        val adapterr = ViewAdapterHiredHomeDetail(dsHired,object : RvInterface {
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

                        binding.rvHiredHomeDetail.adapter = adapterr
                        binding.rvHiredHomeDetail.layoutManager = LinearLayoutManager(activity,
                            LinearLayoutManager.HORIZONTAL,false)
                    }else{
                        getDataHired()
                    }
                }

                override fun onFailure(call: Call<List<Hired>>, t: Throwable) {
                    Log.e("TestGetData Hired_location Thất Bại: ","${t}")
                }
            }

        )

    }

    private fun getDataHired_titlejob_location(titlejobb: String, locationn: String) {
        ApiSever.retrofit.getHired_titlejob_location(titlejobb,locationn).enqueue(
            object: Callback<List<Hired>> {
                override fun onResponse(
                    call: Call<List<Hired>>,
                    response: Response<List<Hired>>
                ) {
                    Log.e("TestGetData Hired_titlejob_location Thành Công: ","Thành Công")
                    var account = response.body()
                    var arr:Int = account?.size as Int
                    Log.e("Size Arr Hired_titlejob_location : ","$arr")
                    if (arr > 0){
                        for (i in 0 .. arr-1){
//                            var x:String = account[i].imgcongviec
                            var diachi:String = "${account[i].diachi},${account[i].tinh_tp}"
                            Log.e("Địa Chỉ: ","$diachi")
                            dsHired.add(DataView(
                                account[i].idcongviec,
                                account[i].idcongty,
                                account[i].username,
                                R.drawable.img_demo_hired,
                                account[i].nganhnghe,
                                account[i].theloai,
                                account[i].chucdanh,
                                account[i].soluong,
                                account[i].mucluong,
                                diachi,
                                account[i].luyy,
                                account[i].ngayketthuc))
                        }
                        val adapterr = ViewAdapterHiredHomeDetail(dsHired,object : RvInterface {
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

                        binding.rvHiredHomeDetail.adapter = adapterr
                        binding.rvHiredHomeDetail.layoutManager = LinearLayoutManager(activity,
                            LinearLayoutManager.HORIZONTAL,false)
                    }else{
                        getDataHired()
                    }
                }

                override fun onFailure(call: Call<List<Hired>>, t: Throwable) {
                    Log.e("TestGetData Hired_titlejob_location Thất Bại: ","${t}")
                }
            }

        )

    }


    companion object {
    }

    private fun getDataHired_titlejob(search:String) {
        ApiSever.retrofit.getHired_titlejob(search).enqueue(
            object: Callback<List<Hired>> {
                override fun onResponse(
                    call: Call<List<Hired>>,
                    response: Response<List<Hired>>
                ) {
                    Log.e("TestGetData Hired_titlejob Thành Công: ","Thành Công")
                    var account = response.body()
                    var arr:Int = account?.size as Int
                    Log.e("Size Arr Hired_titlejob: ","$arr")
                    if (arr > 0){
                        for (i in 0 .. arr-1){
//                            var x:String = account[i].imgcongviec
                            var diachi:String = "${account[i].diachi},${account[i].tinh_tp}"
                            Log.e("Địa Chỉ: ","$diachi")
                            dsHired.add(DataView(
                                account[i].idcongviec,
                                account[i].idcongty,
                                account[i].username,
                                R.drawable.img_demo_hired,
                                account[i].nganhnghe,
                                account[i].theloai,
                                account[i].chucdanh,
                                account[i].soluong,
                                account[i].mucluong,
                                diachi,
                                account[i].luyy,
                                account[i].ngayketthuc))
                        }
                        val adapterr = ViewAdapterHiredHomeDetail(dsHired,object : RvInterface {
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

                        binding.rvHiredHomeDetail.adapter = adapterr
                        binding.rvHiredHomeDetail.layoutManager = LinearLayoutManager(activity,
                            LinearLayoutManager.HORIZONTAL,false)

                    }else{
                        getDataHired()
                    }
                }

                override fun onFailure(call: Call<List<Hired>>, t: Throwable) {
                    Log.e("TestGetData Hired_titlejob Thất Bại: ","${t}")
                }
            }

        )
    }
}