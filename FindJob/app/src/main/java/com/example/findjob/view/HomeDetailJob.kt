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
import com.example.findjob.databinding.FragmentHomeDetailJobBinding
import com.example.findjob.model.DataView
import com.example.findjob.model.Hired
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeDetailJob : Fragment() {
    lateinit var binding: FragmentHomeDetailJobBinding
    var dsHired = mutableListOf<DataView>()
    var dsHiredIt = mutableListOf<DataView>()
    var dsHiredKd = mutableListOf<DataView>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeDetailJobBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getDataHired()
        getDataHiredIt()
        getDataHiredTCKD()
    }

    private fun getDataHiredTCKD() {
        ApiSever.retrofit.getHired_titlejob("Tài chính kinh doanh").enqueue(
            object: Callback<List<Hired>> {
                override fun onResponse(
                    call: Call<List<Hired>>,
                    response: Response<List<Hired>>
                ) {
                    Log.e("Home Search Hired_TCKD Thành Công: ","Thành Công")
                    var account = response.body()
                    var arr:Int = account?.size as Int
                    Log.e("Size Arr Hired_IT: ","$arr")
                    for (i in 0 .. arr-1){
//                            var x:String = account[i].imgcongviec
                        var diachi:String = "${account[i].diachi},${account[i].tinh_tp}"
                        Log.e("Địa Chỉ Hired_IT: ","$diachi")
                        dsHiredKd.add(DataView(
                            account[i].idcongviec,
                            account[i].idcongty,
                            account[i].username,
                            R.drawable.img_tckd,
                            account[i].nganhnghe,
                            account[i].theloai,
                            account[i].chucdanh,
                            account[i].soluong,
                            account[i].mucluong,diachi,
                            account[i].luyy,
                            account[i].ngayketthuc))
                    }
                    val adapterr = ViewAdapterHiredTCKDHomeSearch(dsHiredKd,object : RvInterface {
                        override fun OnClickRv(pos: Int) {
                            Toast.makeText(activity,"Bạn vừa click vào ${dsHiredKd[pos].tenCongViec}", Toast.LENGTH_LONG).show()
                            val i = Intent(activity,JobDetail::class.java)
                            i.putExtra("idcongviec",dsHiredKd[pos].idcongviec)
                            i.putExtra("tencongviec","${dsHiredKd[pos].tenCongViec}")
                            i.putExtra("vitri","${dsHiredKd[pos].chucdanh}")
                            i.putExtra("diachi","${dsHiredKd[pos].diaChi}")
                            i.putExtra("theloai","${dsHiredKd[pos].theLoai}")
                            i.putExtra("luyy","${dsHiredKd[pos].luyy}")
                            i.putExtra("mucluong","${dsHiredKd[pos].mucluong}")
                            i.putExtra("soluong",dsHiredKd[pos].soLuong)
                            Log.e("Số Lượng: ","${dsHiredKd[pos].soLuong}")
                            i.putExtra("ngayketthuc","${dsHiredKd[pos].ngayketthuc}")
                            startActivity(i)
                        }
                    })

                    binding.rvHomeDetailKinhdoanhJob.adapter = adapterr
                    binding.rvHomeDetailKinhdoanhJob.layoutManager = LinearLayoutManager(activity,
                        LinearLayoutManager.HORIZONTAL,false)


                }

                override fun onFailure(call: Call<List<Hired>>, t: Throwable) {
                    Log.e("Home Search Hired_TCKD Thất Bại: ","${t}")
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
                    Log.e("Home Search Hired News Thành Công: ","Thành Công")
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

                    binding.rvHomeDetailNewsJob.adapter = adapterr
                    binding.rvHomeDetailNewsJob.layoutManager = LinearLayoutManager(activity,
                        LinearLayoutManager.HORIZONTAL,false)


                }

                override fun onFailure(call: Call<List<Hired>>, t: Throwable) {
                    Log.e("Home Search Hired News Thất Bại: ","${t}")
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
                    Log.e("Home Search Hired_IT Thành Công: ","Thành Công")
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
                    val adapterr = ViewAdapterHiredITHomeSearch(dsHiredIt,object : RvInterface {
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

                    binding.rvHomeDetailItJob.adapter = adapterr
                    binding.rvHomeDetailItJob.layoutManager = LinearLayoutManager(activity,
                        LinearLayoutManager.HORIZONTAL,false)


                }

                override fun onFailure(call: Call<List<Hired>>, t: Throwable) {
                    Log.e("Home Search Hired_IT Thất Bại: ","${t}")
                }
            }

        )
    }

    companion object {}
}