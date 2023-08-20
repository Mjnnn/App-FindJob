package com.example.findjob.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.findjob.R
import com.example.findjob.controller.ApiSever
import com.example.findjob.controller.RvInterface
import com.example.findjob.controller.ViewAdapterRating
import com.example.findjob.databinding.ActivityNotificationCompanyDetailBinding
import com.example.findjob.model.Company
import com.example.findjob.model.DataView
import com.example.findjob.model.RatingCompany
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NotificationCompanyDetail : AppCompatActivity() {
    lateinit var binding: ActivityNotificationCompanyDetailBinding
    var dsRt = mutableListOf<DataView>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNotificationCompanyDetailBinding.inflate(layoutInflater)
        var i = intent
        var idcongty = i.getIntExtra("idtopiccompany",0)
        if(idcongty != 0 ){
            getDataCompany(idcongty)
        }else{

        }

        setContentView(binding.root)
    }

    private fun getDataCompany(idcongty: Int) {
        ApiSever.retrofit.getNotificationCompanyDetail(idcongty).enqueue(
            object : Callback<List<Company>>{
                override fun onResponse(
                    call: Call<List<Company>>,
                    response: Response<List<Company>>
                ) {
                    var company = response.body()
                    var arr:Int = company?.size as Int
                    if(arr>0){
                        for (i in 0.. arr-1){
                            binding.txtTencongtyDetail.text = company[i].tencongty
                            binding.txtDiachiCompanyDetail.text = "Địa chỉ: ${company[i].diachi},${company[i].tinh_tp}"
                            binding.txtTrangthaictyCompanyDetail.text = "Trạng thái: ${company[i].trangthaicty}"
                            binding.txtSoluocctyCompanyDetail.text = "${company[i].soluoccty}"
                            binding.txtSonvCompanydetail.text = company[i].sonv
                            binding.txtLoaihinhhdCompanyDetail.text = company[i].loaihinhhd
                            binding.txtTennguoilhCompanyDetail.text = company[i].tennguoilh
                            binding.txtEmailCompanyDetail.text = company[i].email
                            binding.txtSdtCompanyDetail.text = company[i].sdt
                            var idcongty = company[i].idcongty
                            getDataRatingCompany(idcongty)
                        }
                    }
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
                                    binding.rvCmtCompanyDetail.layoutManager = LinearLayoutManager(this@NotificationCompanyDetail,
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

                override fun onFailure(call: Call<List<Company>>, t: Throwable) {
                    TODO("Not yet implemented")
                }
            }
        )
    }
}