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
import com.example.findjob.databinding.FragmentSearchDetailJobBinding
import com.example.findjob.model.DataView
import com.example.findjob.model.Hired
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchDetailJob : Fragment() {
        lateinit var binding: FragmentSearchDetailJobBinding
        var dsHired = mutableListOf<DataView>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchDetailJobBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.txtErrorSearchDetailJob.visibility = View.GONE
        val searchJob = Session["searchjob"] as String
        if(searchJob != ""){
            getDataSearchJob(searchJob)
        }
    }

    private fun getDataSearchJob(searchJob: String) {
        ApiSever.retrofit.getSearchDetailJob(searchJob).enqueue(
            object: Callback<List<Hired>> {
                override fun onResponse(
                    call: Call<List<Hired>>,
                    response: Response<List<Hired>>
                ) {
                    Log.e("Search DetailJob Thành Công: ","Thành Công")
                    var account = response.body()
                    var arr:Int = account?.size as Int
                    Log.e("Size Arr Hired_titlejob: ","$arr")
                    if (arr > 0){
                        binding.txtResultSearchDetailJob.text = "Kết quả tìm kiếm: $arr"
                        for (i in 0 .. arr-1){
//                            var x:String = account[i].imgcongviec
                            var diachi:String = "${account[i].diachi},${account[i].tinh_tp}"
                            Log.e("Địa Chỉ: ","$diachi")
                            dsHired.add(
                                DataView(
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
                                account[i].ngayketthuc)
                            )
                        }
                        val adapterr = ViewAdapterListSearchJob(dsHired,object : RvInterface {
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

                        binding.rvSearchDetailJob.adapter = adapterr
                        binding.rvSearchDetailJob.layoutManager = LinearLayoutManager(activity,
                            LinearLayoutManager.VERTICAL,false)

                    }else{
                        binding.txtResultSearchDetailJob.text = "Kết quả tìm kiếm: 0"
                        binding.txtErrorSearchDetailJob.visibility = View.VISIBLE
                    }
                }

                override fun onFailure(call: Call<List<Hired>>, t: Throwable) {
                    Log.e("Search DetailJob  Thất Bại: ","${t}")
                }
            }

        )
    }

    companion object {}
}