package com.example.findjob.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.findjob.R
import com.example.findjob.databinding.ActivityMainBinding
import com.example.findjob.databinding.ActivitySignInBinding

class SignIn : AppCompatActivity() {
    lateinit var binding:ActivitySignInBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        binding.btnDangkySignIn.setOnClickListener {
            var user = binding.edUserSignIn.text.toString()
            var email = binding.edEmailSignIn.text.toString()
            var phone = binding.edPhoneSignIn.text.toString()
            var pass = binding.edPasswordSignIn.text.toString()
            var checkpass = binding.edCheckpasswordSignIn.text.toString()

            binding.txtUserSignIn.text=""
            binding.txtEmailSignIn.text=""
            binding.txtPhoneSignIn.text=""
            binding.txtPasswordSignIn.text=""
            binding.txtCheckpasswordSignIn.text=""

            if(user == ""){
                binding.txtUserSignIn.text = "Vui lòng nhập tài khoản cần đăng ký"
            }

            if(email == ""){
                binding.txtEmailSignIn.text = "Vui lòng nhập email của bạn"
            }

            if(phone == ""){
                binding.txtPhoneSignIn.text = "Vui lòng nhập số điện thoại của bạn"
            }else if(phone.length <10){
                binding.txtPhoneSignIn.text = "Số điện thoại phải ít nhất 10 số"
            }
            if(pass == ""){
                binding.txtPasswordSignIn.text = "Vui lòng điền mật khẩu của bạn"
            }else if(pass.length <8 ){
                binding.txtPasswordSignIn.text = "Mật khẩu phải dài quá 7 ký tự"
            }

            if(checkpass == ""){
                binding.txtCheckpasswordSignIn.text = "Vui lòng nhập lại mật khẩu"
            }else{
                if(checkpass != pass){
                    binding.txtCheckpasswordSignIn.text = "Mật khẩu không khớp,vui lòng thử lại"
                }
            }
            var txtuser = binding.txtUserSignIn.text.toString()
            var txtemail = binding.txtEmailSignIn.text.toString()
            var txtphone = binding.txtPhoneSignIn.text.toString()
            var txtpass = binding.txtPasswordSignIn.text.toString()
            var txtcheckpass = binding.txtCheckpasswordSignIn.text.toString()
            if(txtuser == "" && txtemail == "" && txtphone == "" && txtpass == "" && txtcheckpass == ""){
                Toast.makeText(this@SignIn,"Đang đăng ký tài khoản, quá trình này có thể diễn ra trong một vài phút...",Toast.LENGTH_LONG).show()
                Log.e("Đang Đăng Ký: ","Checking...")
            }
        }
        setContentView(binding.root)
    }
}