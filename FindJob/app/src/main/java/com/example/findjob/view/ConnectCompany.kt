package com.example.findjob.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.CursorAdapter
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.findjob.R
import com.example.findjob.controller.CustomSpinnerAdapter
import com.example.findjob.databinding.FragmentConnectCompanyBinding

class ConnectCompany : Fragment() {
    lateinit var binding:FragmentConnectCompanyBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentConnectCompanyBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Spinner()
        binding.btnBackConnectCompany.setOnClickListener {
            findNavController().navigate(R.id.action_connectCompany_to_manageCompany)
        }
        binding.btnDangKi.setOnClickListener {
            Toast.makeText(activity,"Đã đăng kí thành công, vui lòng đợi admin xác nhận",Toast.LENGTH_LONG).show()
        }
    }

    private fun Spinner() {
        val list = resources.getStringArray(R.array.LoaiHinhHoatDong)
        val list1 = resources.getStringArray(R.array.TrangThaiCongty)
        val list2 = resources.getStringArray(R.array.SoNhanVien)
        val list3 = resources.getStringArray(R.array.QuocGia)
        val list4 = resources.getStringArray(R.array.TinhThanh)
        val adt = activity?.let { ArrayAdapter(it,R.layout.layout_txt_sp, list) }
        val adt1 = activity?.let { ArrayAdapter(it,R.layout.layout_txt_sp, list1) }
        val adt2 = activity?.let { ArrayAdapter(it,R.layout.layout_txt_sp, list2) }
        val adt3 = activity?.let { ArrayAdapter(it,R.layout.layout_txt_sp, list3) }
        val adt4 = activity?.let { ArrayAdapter(it,R.layout.layout_txt_sp, list4) }
        //gọi spiner
        binding.spnLoaiHinhHoatDong.adapter = adt
        binding.spnTrangThaiCongTy.adapter = adt1
        binding.spnSoNhanVien.adapter = adt2
        binding.spnQuocGia.adapter = adt3
        binding.spnTinhThanh.adapter = adt4
    }
    companion object {}
}