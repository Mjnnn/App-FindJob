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
import com.example.findjob.controller.Session
import com.example.findjob.databinding.FragmentSignInDetailBinding

class SignInDetail : Fragment() {

    lateinit var binding:FragmentSignInDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignInDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnDangkySignIn.setOnClickListener {
            var user = binding.edUserSignIn.text.toString()
            var email = binding.edEmailSignIn.text.toString()
            var phone = binding.edPhoneSignIn.text.toString()
            var pass = binding.edPasswordSignIn.text.toString()
            var checkpass = binding.edCheckpasswordSignIn.text.toString()

            binding.txtUserSignIn.text = ""
            binding.txtEmailSignIn.text = ""
            binding.txtPhoneSignIn.text = ""
            binding.txtPasswordSignIn.text = ""
            binding.txtCheckpasswordSignIn.text = ""

            if (user == "") {
                binding.txtUserSignIn.text = "Vui lòng nhập tài khoản cần đăng ký"
            }

            if (email == "") {
                binding.txtEmailSignIn.text = "Vui lòng nhập email của bạn"
            }

            if (phone == "") {
                binding.txtPhoneSignIn.text = "Vui lòng nhập số điện thoại của bạn"
            } else if (phone.length < 10) {
                binding.txtPhoneSignIn.text = "Số điện thoại phải ít nhất 10 số"
            }
            if (pass == "") {
                binding.txtPasswordSignIn.text = "Vui lòng điền mật khẩu của bạn"
            } else if (pass.length < 8) {
                binding.txtPasswordSignIn.text = "Mật khẩu phải dài quá 7 ký tự"
            }

            if (checkpass == "") {
                binding.txtCheckpasswordSignIn.text = "Vui lòng nhập lại mật khẩu"
            } else {
                if (checkpass != pass) {
                    binding.txtCheckpasswordSignIn.text = "Mật khẩu không khớp,vui lòng thử lại"
                }
            }
            var txtuser = binding.txtUserSignIn.text.toString()
            var txtemail = binding.txtEmailSignIn.text.toString()
            var txtphone = binding.txtPhoneSignIn.text.toString()
            var txtpass = binding.txtPasswordSignIn.text.toString()
            var txtcheckpass = binding.txtCheckpasswordSignIn.text.toString()
            if (txtuser == "" && txtemail == "" && txtphone == "" && txtpass == "" && txtcheckpass == "") {
                Toast.makeText(
                    activity,
                    "Đang đăng ký tài khoản, quá trình này có thể diễn ra trong một vài phút...",
                    Toast.LENGTH_LONG
                ).show()
                Log.e("Đang Đăng Ký: ", "Checking...")
                val pages = Session["pages"] as Int
                if(pages == 1){
                    findNavController().navigate(R.id.action_signInDetail2_to_login2)
                }else{
                    Session.set("pages",0)
                    findNavController().navigate(R.id.action_signInDetail_to_login)
                }
            }else{
                Toast.makeText(
                    activity,
                    "Đăng ký không thành công, vui lòng kiểm tra lại!",
                    Toast.LENGTH_LONG
                ).show()
                Log.e("Đăng Ký Không Thành Công: ", "Faile...")
            }
        }
    }


    companion object {

    }
}