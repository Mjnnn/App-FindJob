package com.example.findjob.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.findjob.R
import com.example.findjob.controller.ApiSever
import com.example.findjob.controller.Session
import com.example.findjob.databinding.FragmentEditProfileBinding
import com.example.findjob.model.Account
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EditProfile : Fragment() {
        lateinit var binding:FragmentEditProfileBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEditProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val user = Session["username"] as String
        if(user != ""){
            getDataUser(user)
        }
        binding.btnBackEditProfile.setOnClickListener {
            findNavController().navigate(R.id.action_editProfile_to_profileDetail)
        }
        binding.btnOutCv.setOnClickListener {
            Toast.makeText(activity,"Ứng dụng sẽ cập nhật tính năng này vào thời gian tới",Toast.LENGTH_LONG).show()
        }
    }

    private fun getDataUser(user: String) {
        ApiSever.retrofit.getAccount(user).enqueue(
            object : Callback<List<Account>> {
                override fun onResponse(
                    call: Call<List<Account>>,
                    response: Response<List<Account>>
                ) {
                    var user = response.body()
                    Log.e("Get Data Edit Account Thành Công ","Thành Công")
                    var accountUser = user?.size as Int
                    if (accountUser >  0){
                        var email:String = user[0].email as String
                        var phonenumber = user[0].phonenumber
                        binding.edEmailProfile.setText( "Email: ${email}")
                        binding.edPhonenumberProfile.setText( "SĐT: ${phonenumber}")
                        Log.e("Email Profile Detail: ","${user[0].email}")
                    }else{
                        Log.e("Arr Profile Detail Rỗng: ","${accountUser}")
                    }

                }

                override fun onFailure(call: Call<List<Account>>, t: Throwable) {
                    Log.e("Get Data Edit Account Không thành công: ","${t}")
                }
            }
        )
    }

    companion object {}
}