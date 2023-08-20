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
import com.example.findjob.databinding.FragmentManageCompanyBinding
import com.example.findjob.model.Company
import com.example.findjob.model.DataView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ManageCompany : Fragment() {
    lateinit var binding:FragmentManageCompanyBinding
    var dsCompany = mutableListOf<DataView>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentManageCompanyBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val user = Session["username"] as String
        if(user != ""){
            ApiSever.retrofit.getListCompany(user).enqueue(
                object : Callback<List<Company>>{
                    override fun onResponse(
                        call: Call<List<Company>>,
                        response: Response<List<Company>>
                    ) {
                        var company = response.body()
                        Log.e("List Company Thành Công: ","Thành Công")
                        var arr:Int = company?.size as Int
                        Log.e("Size Arr List Company: ","$arr")
                        if(arr>0){
                            binding.txtErrorListCompany.visibility = View.GONE
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
                                        R.drawable.img_company,
                                        R.drawable.img_demo_company,
                                        company[i].linkwebsite,
                                        company[i].phucloicty,
                                        company[i].masothue,
                                        company[i].trangthaichitiet,
                                        company[i].ngaydangky
                                    )
                                )
                            }
                            val adapterr = ViewAdapterListCompany(dsCompany,object : RvInterface {
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

                            binding.rvListCompany.adapter = adapterr
                            binding.rvListCompany.layoutManager = LinearLayoutManager(activity,
                                LinearLayoutManager.VERTICAL,false)
                        }else{

                        }
                    }

                    override fun onFailure(call: Call<List<Company>>, t: Throwable) {
                        Log.e("List Company Thất Bại: ","$t")
                    }
                }
            )
        }

        binding.btnAddCompany.setOnClickListener {
            findNavController().navigate(R.id.action_manageCompany_to_connectCompany)
        }
        binding.btnBackCompany.setOnClickListener {
            findNavController().navigate(R.id.action_manageCompany_to_profileDetail)
        }
    }

    companion object {}
}