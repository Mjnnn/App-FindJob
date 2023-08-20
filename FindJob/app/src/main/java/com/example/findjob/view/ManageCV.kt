package com.example.findjob.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.findjob.R
import com.example.findjob.controller.RvInterface
import com.example.findjob.controller.ViewAdapterListBlog
import com.example.findjob.controller.ViewAdapterListCV
import com.example.findjob.databinding.FragmentManageCVBinding
import com.example.findjob.model.DataView

class ManageCV : Fragment() {
        lateinit var binding: FragmentManageCVBinding
        var dsCV = mutableListOf<DataView>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentManageCVBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        spiner_qlcv()
        listDataCV()
        binding.btnBackManageCv.setOnClickListener {
            findNavController().navigate(R.id.action_manageCV_to_profileDetail)
        }
    }

    private fun listDataCV() {
        dsCV.add(DataView("Mẫu CV Lập Trình Viên","Kinh Nghiệm","12-05-2023",R.drawable.img_demo_cv_1))
        dsCV.add(DataView("Mẫu CV Basic","Đơn Giản","11-05-2023",R.drawable.img_demo_cv_2))
        dsCV.add(DataView("Mẫu CV Gradient","Màu Sắc","12-05-2023",R.drawable.img_demo_cv_3))
        dsCV.add(DataView("Mẫu CV Outstanding ","Công Nghệ","13-05-2023",R.drawable.img_demo_cv_4))
        dsCV.add(DataView("Mẫu CV Senior","Xã Hội","12-05-2023",R.drawable.img_demo_cv_5))
        dsCV.add(DataView("Mẫu CV Toppier","Xã Hội","11-05-2023",R.drawable.img_demo_cv_6))

        var adapterr = ViewAdapterListCV(dsCV,object : RvInterface {
            override fun OnClickRv(pos: Int) {
                Toast.makeText(activity,"Bạn vừa click vào ${dsCV[pos].titlecv}", Toast.LENGTH_LONG).show()
            }
        })
        binding.rvManageListCv.adapter = adapterr
        binding.rvManageListCv.layoutManager =  LinearLayoutManager(activity,
            LinearLayoutManager.VERTICAL,false)

    }

    private fun spiner_qlcv() {
        val list = resources.getStringArray(R.array.spn_1)
        val list1 = resources.getStringArray(R.array.spn_2)
        val list2 = resources.getStringArray(R.array.spn_3)
        val adapt = activity?.let { ArrayAdapter(it,R.layout.layout_txt_sp, list)}
        val adapt1 =activity?.let { ArrayAdapter(it,R.layout.layout_txt_sp, list1)}
        val adapt2 = activity?.let { ArrayAdapter(it,R.layout.layout_txt_sp, list2)}
        binding.spn1.adapter = adapt
        binding.spn2.adapter = adapt1
        binding.spn3.adapter = adapt2
    }

    companion object {}
}