package com.example.findjob.view

import android.app.DatePickerDialog
import android.icu.util.Calendar
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.DatePicker
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.findjob.R
import com.example.findjob.controller.ApiSever
import com.example.findjob.controller.Session
import com.example.findjob.databinding.FragmentPostJobBinding
import com.example.findjob.model.MyResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

class PostJob : Fragment() {
        lateinit var binding: FragmentPostJobBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPostJobBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnBackPostJob.setOnClickListener {
            findNavController().navigate(R.id.action_postJob_to_manageJob)
        }
        spiner_dtcv()
        val myCalendar = Calendar.getInstance()
        var year = myCalendar.get(Calendar.YEAR)
        var month = myCalendar.get(Calendar.MONTH)
        var day = myCalendar.get(Calendar.DAY_OF_MONTH)
        var ngaydangky = "$year/${month+1}/$day"
        var ngayketthuc = "2023-05-17"
        binding.btnDatePicker.setOnClickListener {
            activity?.let { it1 ->
                DatePickerDialog(it1,DatePickerDialog.OnDateSetListener { view, year, monthh, dayOfMonth ->
                        binding.txtDate.setText("$dayOfMonth/${monthh+1}/$year")
                       ngayketthuc = "$year-${monthh+1}-$dayOfMonth"
                    Log.e("END DAY","$ngayketthuc")
                },year,month,day).show()
            }
        }
        binding.btnSubmitJob.setOnClickListener {
            val user = Session["username"] as String
            Log.e("SESION USERNAME POST JOB " , "$user")
            var idcongty = 1
            var nganhnghe = "Công nghệ thông tin"
            var theloai = "Hợp Đồng"
            var chucdanh = binding.edChucdanh.text.toString()
            var soluong = binding.edSoLuong.text.toString().toInt()
            var mucluong = binding.edtMucLuong.text.toString()
            var luyy = binding.edtLuyy.text.toString()
            postJob(user,idcongty,nganhnghe,theloai,chucdanh,soluong,mucluong,ngaydangky,ngayketthuc,luyy)
        }

    }

    private fun postJob(
        user: String,
        idcongty: Int,
        nganhnghe: String,
        theloai: String,
        chucdanh: String,
        soluong: Int,
        mucluong: String,
        ngaydangky: String,
        ngayketthuc: String,
        luyy: String
    ) {
        ApiSever.retrofit.postJob(user,idcongty,nganhnghe,theloai,chucdanh,soluong,mucluong,ngaydangky,ngayketthuc,luyy).enqueue(
            object : Callback<MyResponse>{
                override fun onResponse(call: Call<MyResponse>, response: Response<MyResponse>) {
                    var result = response.body()
                    var result2 = result?.resultt
                    Log.e("Đã Post Job: " ,"Thành Công")
                    if(result2 == true ){
                        Toast.makeText(activity,"Đăng tuyển thành công!!!",Toast.LENGTH_LONG).show()
                    }else{
                        Toast.makeText(activity,"Đăng tuyển không thành công!!!, vui lòng kiểm tra lại",Toast.LENGTH_LONG).show()
                    }
                }

                override fun onFailure(call: Call<MyResponse>, t: Throwable) {
                    Log.e("Post Job Không Thành Công: " ,"$t")
                }
            }
        )

    }


    private fun updateLable(myCalendar: Calendar) {
            val myFormat = "dd-MM-yyy"
            val sdf = SimpleDateFormat(myFormat, Locale.UK)
           binding.txtDate.setText((sdf.format(myCalendar.time)))
        }

    private fun spiner_dtcv() {
        val ds = resources.getStringArray(R.array.sp_ChonCT)
        val ds1 = resources.getStringArray(R.array.sp_ChonNN)
        val ds2 = resources.getStringArray(R.array.sp_ChonTL)
        val adtt = activity?.let{ ArrayAdapter(it,R.layout.layout_txt_sp, ds)}
        val adtt1 = activity?.let{ ArrayAdapter(it,R.layout.layout_txt_sp, ds1)}
        val adtt2 = activity?.let{ ArrayAdapter(it,R.layout.layout_txt_sp, ds2)}
        binding.spChonCT.adapter = adtt
        binding.spChonNN.adapter = adtt1
        binding.spChonTL.adapter = adtt2
    }
    companion object {

    }
}