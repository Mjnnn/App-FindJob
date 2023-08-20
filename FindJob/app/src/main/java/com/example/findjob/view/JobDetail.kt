package com.example.findjob.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.findjob.R
import com.example.findjob.controller.*
import com.example.findjob.databinding.ActivityJobDetailBinding
import com.example.findjob.model.DataView
import com.example.findjob.model.Hired
import com.example.findjob.model.MyResponse
import com.example.findjob.model.RatingJob
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class JobDetail : AppCompatActivity() {
    lateinit var binding:ActivityJobDetailBinding
    var dsHired = mutableListOf<DataView>()
    var dsRt = mutableListOf<DataView>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityJobDetailBinding.inflate(layoutInflater)

        val i = intent
        var idcongviec = i.getIntExtra("idcongviec",0)
        Log.e("ID Công Việc Job Detail","$idcongviec")
        var tencongviec:String = i.getStringExtra("tencongviec") as String
        var vitri = i.getStringExtra("vitri")
        var theloai = i.getStringExtra("theloai")
        var diachi = i.getStringExtra("diachi")
        var luyy = i.getStringExtra("luyy")
        var mucluong = i.getStringExtra("mucluong")
        var soluong = i.getIntExtra("soluong",0)
        Log.e("Số Lượng Job Detail","$soluong")
        var ngayketthuc = i.getStringExtra("ngayketthuc")

        binding.txtTencongviecJobDetail.text = tencongviec
        binding.txtVitriJobDetail.text = "Vị trí: $vitri"
        binding.txtTheloaiJobDetail.text = "Hình thức: $theloai"
        binding.txtDiachiJobDetail.text = "Địa chỉ: $diachi"
        binding.txtLuyyJobDetail.text = "Lưu ý: $luyy"
        binding.txtMucluongJobDetail.text = "Mức lương: $mucluong$"
        binding.txtSoluongJobDetail.text = "Số lượng: $soluong"
        binding.txtNgayketthucJobDetail.text = ngayketthuc

        binding.txtLuyyJobDetail.maxLines = 5

        getDataHired(tencongviec)
        getDataRating(idcongviec)

        binding.btnSubmitRt.setOnClickListener {
            var user = Session["username"] as String
            var content = binding.edRtJob.text.toString()
            if(user != ""){
                if(content == " " || content == ""){
                    Toast.makeText(this,"Nội dung không được để trống",Toast.LENGTH_LONG).show()
                }else{
                    postRating(idcongviec,"job",user,content)
                }
            }else{
                Toast.makeText(this,"Bạn chưa đăng nhập, Hãy đăng nhập để bình luận",Toast.LENGTH_LONG).show()
            }


        }

        setContentView(binding.root)
    }

    private fun postRating(idcongviec: Int, s: String, user: String, content: String) {
            ApiSever.retrofit.postRating(idcongviec,s,user,content).enqueue(
                object : Callback<MyResponse>{
                    override fun onResponse(
                        call: Call<MyResponse>,
                        response: Response<MyResponse>
                    ) {
                        Log.e("Đăng tải Bình Luận Công Việc Thành Công: ","Thành Công")
                        var result = response.body()
                        var result2 = result?.resultt
                        if(result2 == true){
                            Toast.makeText(this@JobDetail,"Bình luận của bạn đã được tải lên",Toast.LENGTH_LONG).show()
                        }else{
                            Toast.makeText(this@JobDetail,"Bình luận của bạn chưa được tải lên",Toast.LENGTH_LONG).show()
                        }
                    }
                    override fun onFailure(call: Call<MyResponse>, t: Throwable) {
                        Log.e("Đăng tải Bình Luận Công Việc Không Thành Công: ","$t")
                    }
                }
            )
    }

    private fun getDataRating(idcongviec:Int) {
        ApiSever.retrofit.getRatingJob(idcongviec).enqueue(
            object: Callback<List<RatingJob>>{
                override fun onResponse(
                    call: Call<List<RatingJob>>,
                    response: Response<List<RatingJob>>
                ) {
                    Log.e("TestGetData RatingJobDetail: ","Thành Công")
                    var rating = response.body()
                    var arr:Int = rating?.size as Int
                    if(arr>0){
                        for (i in 0 .. arr-1){
                            dsRt.add(
                                DataView(
                                rating[i].idrtjob,
                                rating[i].username,
                                rating[i].idcongviec,
                                rating[i].content,
                                rating[i].ratingjob,
                                rating[i].time,
                                R.drawable.tlen2
                            )
                            )
                        }
                        val adapterr = ViewAdapterRating(dsRt,object : RvInterface{
                            override fun OnClickRv(pos: Int) {

                            }
                        })
                        binding.rvCmtJobDetail.adapter = adapterr
                        binding.rvCmtJobDetail.layoutManager = LinearLayoutManager(this@JobDetail,
                            LinearLayoutManager.VERTICAL,false)
                    }else{
                        Log.e("TestGetData RatingJobDetail Thành Công: ","Không có phản hồi nào!")
                    }
                }

                override fun onFailure(call: Call<List<RatingJob>>, t: Throwable) {
                    Log.e("TestGetData RatingJobDetail Thất Bại: ","${t}")
                }
            }
        )
    }

    private fun getDataHired(tencongviec:String) {
        ApiSever.retrofit.getHired_titlejob(tencongviec).enqueue(
            object: Callback<List<Hired>> {
                override fun onResponse(
                    call: Call<List<Hired>>,
                    response: Response<List<Hired>>
                ) {
                    Log.e("TestGetData Hired_JobDetail: ","Thành Công")
                    var account = response.body()
                    var arr:Int = account?.size as Int
                    Log.e("Size Arr Hired_JobDetail: ","$arr")
                    if (arr > 0){
                        for (i in 0 .. arr-1){
//                            var x:String = account[i].imgcongviec
                            var diachi:String = "${account[i].diachi},${account[i].tinh_tp}"
                            Log.e("Địa Chỉ: ","$diachi")
                            dsHired.add(DataView(
                                account[i].idcongviec,
                                account[i].idcongty,
                                account[i].username,
                                R.drawable.img_demo_hired_it,
                                account[i].nganhnghe,
                                account[i].theloai,
                                account[i].chucdanh,
                                account[i].soluong,
                                account[i].mucluong,
                                diachi,
                                account[i].luyy,
                                account[i].ngayketthuc))
                        }
                        val adapterr = ViewAdapterHirerITHomeDetail(dsHired,object : RvInterface {
                            override fun OnClickRv(pos: Int) {
                                Toast.makeText(this@JobDetail,"Bạn vừa click vào ${dsHired[pos].tenCongViec}", Toast.LENGTH_LONG).show()
                                val i = Intent(this@JobDetail,JobDetail::class.java)
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

                        binding.rvCongvieclienquan.adapter = adapterr
                        binding.rvCongvieclienquan.layoutManager = LinearLayoutManager(this@JobDetail,
                            LinearLayoutManager.HORIZONTAL,false)

                    }else{
//                        getDataHired()
                    }
                }

                override fun onFailure(call: Call<List<Hired>>, t: Throwable) {
                    Log.e("TestGetData Hired_JobDetail Thất Bại: ","${t}")
                }
            }

        )
    }
}