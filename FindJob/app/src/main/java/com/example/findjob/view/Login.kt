package com.example.findjob.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
//import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import com.example.findjob.R
import com.example.findjob.controller.ApiSever
import com.example.findjob.controller.Session
import com.example.findjob.databinding.FragmentLoginBinding
import com.example.findjob.model.Company
import com.example.findjob.model.MyResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class Login : Fragment() {
    lateinit var binding:FragmentLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.txtUsernameProfile.visibility = View.GONE
        binding.txtPasswordProfile.visibility = View.GONE
        val user = Session["username"] as String
        if(user != ""){
            findNavController().navigate(R.id.action_login_to_profileDetail)
        }
        Log.e("ED Username ban đầu: ","${binding.edUsernameProfile.text.toString()}")
        Log.e("ED Password ban đầu: ","${binding.edPasswordProfile.text.toString()}")
        binding.btnLoginProfile.setOnClickListener {
            var username = binding.edUsernameProfile.text.toString()
            var password = binding.edPasswordProfile.text.toString()
            Log.e("ED Username Sau khi click: ","${username}")
            Log.e("ED Password Sau khi click: ","${password}")
            if(username == "" && password == ""){
                binding.txtUsernameProfile.visibility = View.VISIBLE
                binding.txtPasswordProfile.visibility = View.VISIBLE
                Log.e("Cả 2 Null: ","1")
            }else if(username == ""){
                binding.txtUsernameProfile.visibility = View.VISIBLE
                binding.txtPasswordProfile.visibility = View.GONE
                Log.e("Cả Username Null: ","2")
            }else if(password == "" || password.length < 7){
                binding.txtPasswordProfile.visibility = View.VISIBLE
                binding.txtUsernameProfile.visibility = View.GONE
                Log.e("Cả Password Null: ","3")
            }else{
                binding.txtUsernameProfile.visibility = View.GONE
                binding.txtPasswordProfile.visibility = View.GONE

                ApiSever.retrofit.getDataLogin(username, password).enqueue(
                    object : Callback<MyResponse> {
                        override fun onResponse(
                            call: Call<MyResponse>,
                            response: Response<MyResponse>
                        ) {
                            Log.e("Bắt Đầu Lấy Dữ Liệu: ","Start")
                            var result = response.body()
                            var resultLogin = result?.resultt
                            if(resultLogin == true){
                                Toast.makeText(activity,"Đăng Nhập Thành Công",Toast.LENGTH_LONG).show()
                                Session.set("username","$username")
                                Log.e("SESSION USERNAME LOGIN: ","${Session["username"] as String}")
                                val pages = Session["pages"] as Int
                                if(pages == 1){
                                    findNavController().navigate(R.id.action_login2_to_notificationDetail)
                                }else{
                                    findNavController().navigate(R.id.action_login_to_profileDetail)
                                }

                            }else{
                                Toast.makeText(activity,"Đăng nhập không thành công, thử lại",Toast.LENGTH_LONG).show()
                            }
                        }

                        override fun onFailure(call: Call<MyResponse>, t: Throwable) {
                            Log.e("Lấy Dữ Liệu Thất Bại: ","Faile_$t")
                        }
                    }
                )
            }
        }


        binding.txtSigninProfile.setOnClickListener {
            val pages = Session["pages"] as Int
            if(pages == 1){
                findNavController().navigate(R.id.action_login2_to_signInDetail2)
            }else{
                findNavController().navigate(R.id.action_login_to_signInDetail)
            }

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {}
}