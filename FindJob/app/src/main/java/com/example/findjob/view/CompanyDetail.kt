package com.example.findjob.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.findjob.R
import com.example.findjob.controller.ApiSever
import com.example.findjob.controller.RvInterface
import com.example.findjob.controller.Session
import com.example.findjob.controller.ViewAdapterRating
import com.example.findjob.databinding.ActivityCompanyDetailBinding
import com.example.findjob.model.DataView
import com.example.findjob.model.RatingBlog
import com.example.findjob.model.RatingCompany
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CompanyDetail : AppCompatActivity() {
    lateinit var binding:ActivityCompanyDetailBinding
    var dsRt = mutableListOf<DataView>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCompanyDetailBinding.inflate(layoutInflater)
        val i = intent
        var idcongty = i.getIntExtra("idcongty",0)
        var username = i.getStringExtra("username") as String
        var tencongty = i.getStringExtra("tencongty")
        var diachi = i.getStringExtra("diachi")
        var tinh_tp = i.getStringExtra("tinh_tp")
        var trangthaicty = i.getStringExtra("trangthaicty")
        var loaihinhhd = i.getStringExtra("loaihinhhd")
        var sonv = i.getStringExtra("sonv")
        var soluoccty = i.getStringExtra("soluoccty")
        var tennguoilh = i.getStringExtra("tennguoilh")
        var sdt = i.getStringExtra("sdt")
        var email = i.getStringExtra("email")

        binding.txtTencongtyDetail.text = tencongty
        binding.txtDiachiCompanyDetail.text = "Địa chỉ: ${diachi},${tinh_tp}"
        binding.txtTrangthaictyCompanyDetail.text = "Trạng thái: $trangthaicty"
        binding.txtSoluocctyCompanyDetail.text = soluoccty
        binding.txtSonvCompanydetail.text = sonv
        binding.txtLoaihinhhdCompanyDetail.text = loaihinhhd
        binding.txtTennguoilhCompanyDetail.text = tennguoilh
        binding.txtEmailCompanyDetail.text = email
        binding.txtSdtCompanyDetail.text = sdt

        getDataRatingCompany(idcongty)
        val user = Session["username"] as String

        Log.e("Session Username CompanyDetail","$user")
        Log.e("Username CompanyDetail","$username")

        val username2 = username.lowercase()
        if(user != username2){
            binding.btnEditConpanyDetail.visibility = View.GONE
            binding.btnDeleteCompanyDetail.visibility = View.GONE
        }

        setContentView(binding.root)
    }

    private fun getDataRatingCompany(idcongty: Int) {
        ApiSever.retrofit.getRatingCompany(idcongty).enqueue(
            object: Callback<List<RatingCompany>> {
                override fun onResponse(
                    call: Call<List<RatingCompany>>,
                    response: Response<List<RatingCompany>>
                ) {
                    Log.e("TestGetData RatingCompanyDetail: ","Thành Công")
                    var rating = response.body()
                    var arr:Int = rating?.size as Int
                    if(arr>0){
                        for (i in 0 .. arr-1){
                            dsRt.add(
                                DataView(
                                    rating[i].idrtcpn,
                                    rating[i].username,
                                    rating[i].idcongty,
                                    R.drawable.tlen2,
                                    rating[i].content,
                                    rating[i].ratingcompany,
                                    rating[i].time,
                                )
                            )
                        }
                        val adapterr = ViewAdapterRating(dsRt,object : RvInterface {
                            override fun OnClickRv(pos: Int) {

                            }
                        })
                        binding.rvCmtCompanyDetail.adapter = adapterr
                        binding.rvCmtCompanyDetail.layoutManager = LinearLayoutManager(this@CompanyDetail,
                            LinearLayoutManager.VERTICAL,false)
                    }else{
                        Log.e("TestGetData RatingBlogDetail Thành Công: ","Không có phản hồi nào!")
                    }
                }

                override fun onFailure(call: Call<List<RatingCompany>>, t: Throwable) {
                    Log.e("TestGetData RatingCompanyDetail Thất Bại: ","${t}")
                }
            }
        )
    }
}