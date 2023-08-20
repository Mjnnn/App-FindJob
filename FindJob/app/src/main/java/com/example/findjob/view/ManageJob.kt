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
import com.example.findjob.databinding.FragmentManageJobBinding
import com.example.findjob.model.DataView
import com.example.findjob.model.Hired
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ManageJob : Fragment() {
    lateinit var binding: FragmentManageJobBinding
    var dsHired = mutableListOf<DataView>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentManageJobBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val user = Session["username"] as String
        if(user != ""){
            getDataListJob(user)
        }
        binding.btnBackJob.setOnClickListener {
            findNavController().navigate(R.id.action_manageJob_to_profileDetail)
        }
        binding.btnAddJob.setOnClickListener {
            findNavController().navigate(R.id.action_manageJob_to_postJob)
        }
    }

    private fun getDataListJob(user: String) {
        ApiSever.retrofit.getListJob(user).enqueue(
            object: Callback<List<Hired>> {
                override fun onResponse(
                    call: Call<List<Hired>>,
                    response: Response<List<Hired>>
                ) {
                    Log.e("Get Data List Job Thành Công: ","Thành Công")
                    var account = response.body()
                    var arr:Int = account?.size as Int
                    Log.e("Size Arr Job: ","$arr")
                    if(arr > 0 ){
                        binding.txtErrorListJob.visibility = View.GONE
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

                        binding.rvListJob.adapter = adapterr
                        binding.rvListJob.layoutManager = LinearLayoutManager(activity,
                            LinearLayoutManager.VERTICAL,false)
                    }



                }

                override fun onFailure(call: Call<List<Hired>>, t: Throwable) {
                    Log.e("Get Data List Job Thất Bại: ","${t}")
                }
            }

        )

    }

    companion object {}
}