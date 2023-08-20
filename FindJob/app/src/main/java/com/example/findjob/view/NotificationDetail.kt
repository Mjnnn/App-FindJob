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
import com.example.findjob.databinding.FragmentNotificationBinding
import com.example.findjob.databinding.FragmentNotificationDetailBinding
import com.example.findjob.model.DataView
import com.example.findjob.model.Notification
import com.example.findjob.model.NotificationAdmin
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NotificationDetail : Fragment() {
   lateinit var binding: FragmentNotificationDetailBinding
    var dsad = mutableListOf<DataView>()
    var ds = mutableListOf<DataView>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNotificationDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val user = Session["username"] as String
            if(user != ""){
                getDataNotificationAdmin(user)
                getDataNotification(user)
            }



    }

    private fun getDataNotification(user: String) {
        ApiSever.retrofit.getNotification(user).enqueue(
            object : Callback<List<com.example.findjob.model.Notification>>{
                override fun onResponse(
                    call: Call<List<Notification>>,
                    response: Response<List<Notification>>
                ) {
                    Log.e("Lấy DataNotification: ", "Thành Công")
                    val notification = response.body()
                    var arr:Int = notification?.size as Int
                    if(arr >0 ){
                        binding.txtErrorNotification.visibility = View.GONE
                        for (i in 0 .. arr-1){
                            val topic = notification[i].topic
                            var title = ""
                            if (topic == "blog"){
                                title = " đã bình luận Blog của bạn"
                            }else if (topic == "company"){
                                title = " đã bình luận Công Ty của bạn"
                            }else{
                                title = " đã bình luận Công Việc"
                            }
                            ds.add(
                                DataView(notification[i].idnotify,
                                            notification[i].usernametopic,
                                            notification[i].username,
                                            notification[i].idtopic,
                                            title,
                                            notification[i].time,
                                            R.drawable.tlen2)
                            )

                        }
                        val adapterr = ViewAdapterNotification(ds,object : RvInterface {
                            override fun OnClickRv(pos: Int) {
                                Toast.makeText(activity,"Bạn vừa click vào thông báo ${ds[pos].topic} Và ${ds[pos].idtopic}",Toast.LENGTH_LONG).show()
                                var topic = ds[pos].topic as String
                                var id = ds[pos].idtopic
                                if( topic == " đã bình luận Blog của bạn"){
                                    var i = Intent(activity,NotificationBlogDetail::class.java)
                                    i.putExtra("idtopicblog",id as Int)
                                    startActivity(i)
                                }else if (topic == " đã bình luận Công Ty của bạn"){
//                                    Session.set("idtopiccompany",id as Int)
                                    var i = Intent(activity,NotificationCompanyDetail::class.java)
                                    i.putExtra("idtopiccompany", id as Int)
                                    startActivity(i)
                                }else{
//                                    Session.set("idtopichired",id as Int)
                                    var i = Intent(activity,NotificationJobDetail::class.java)
                                    i.putExtra("idtopichired", id as Int)
                                    startActivity(i)
                                }
                            }
                        })
                        binding.rvNotificationDetail.adapter = adapterr
                        binding.rvNotificationDetail.layoutManager =  LinearLayoutManager(activity,
                            LinearLayoutManager.VERTICAL,false)
                    }
                }

                override fun onFailure(call: Call<List<Notification>>, t: Throwable) {
                    Log.e("Lấy DataNotification Thất Bại: ", "$t")
                }
            }
        )
    }

    private fun getDataNotificationAdmin(user: String) {
        ApiSever.retrofit.getNotificationAdmin(user).enqueue(
            object: Callback<List<NotificationAdmin>>{
                override fun onResponse(
                    call: Call<List<NotificationAdmin>>,
                    response: Response<List<NotificationAdmin>>
                ) {
                    Log.e("Lấy DataNotificationAdmin: ", "Thành Công")
                    val notificationAdmin = response.body()
                    var arr:Int = notificationAdmin!!.size
                    if(arr>0){
                     binding.txtErrorNotificationadmin.visibility = View.GONE
                        for ( i in 0 .. arr-1){
                            val trangthai = notificationAdmin[i].trangthai
                            val topic =  notificationAdmin[i].topic
                            var title = ""
                            if (trangthai == "chấp nhận"){
                                if(topic == "Blog"){
                                    title = " đã chấp nhận Blog của bạn"
                                }else if(topic == "Công Ty"){
                                    title = " đã chấp nhận Công Ty của bạn"
                                }else{
                                    title = " đã chấp nhận Công Việc của bạn"
                                }
                            }else{
                                if(topic == "Blog"){
                                    title = " đã từ chối Blog của bạn"
                                }else if(topic == "Công Ty"){
                                    title = " đã từ chối Công Ty của bạn"
                                }else{
                                    title = " đã từ chối Công Việc của bạn"
                                }
                            }
                            dsad.add(DataView(notificationAdmin[i].idnotify,
                                              notificationAdmin[i].usernamead,
                                              notificationAdmin[i].username,
                                              notificationAdmin[i].idtopic,
                                              title,
                                              notificationAdmin[i].trangthai,
                                              notificationAdmin[i].time,
                                              R.drawable.img_notification_admin))
                        }
                        val adapterr = ViewAdapterNotificationAdmin(dsad,object : RvInterface {
                            override fun OnClickRv(pos: Int) {
                                Toast.makeText(activity,"Bạn vừa click vào thông báo ${dsad[pos].topic}",Toast.LENGTH_LONG).show()
                                var topic = dsad[pos].topic as String
                                var id = dsad[pos].idtopic
                                if( topic == " đã chấp nhận Blog của bạn"){
//                                    Session.set("idtopicblog",id as Int)
                                    var i = Intent(activity,NotificationBlogDetail::class.java)
                                        i.putExtra("idtopicblog",id as Int)
                                    startActivity(i)
                                }else if (topic == " đã chấp nhận Công Ty của bạn"){
//                                    Session.set("idtopiccompany",id as Int)
                                    var i = Intent(activity,NotificationCompanyDetail::class.java)
                                        i.putExtra("idtopiccompany", id as Int)
                                    startActivity(i)
                                }else{
//                                    Session.set("idtopichired",id as Int)
                                    var i = Intent(activity,NotificationJobDetail::class.java)
                                    i.putExtra("idtopichired", id as Int)
                                    startActivity(i)
                                }
                            }
                        })
                        binding.rvNotificationadminDetail.adapter = adapterr
                        binding.rvNotificationadminDetail.layoutManager = LinearLayoutManager(activity,
                            LinearLayoutManager.VERTICAL,false)

                    }
                }

                override fun onFailure(call: Call<List<NotificationAdmin>>, t: Throwable) {
                    Log.e("Lấy DataNotificationAdmin Thất Bại: ", "$t")
                }
            }
        )

    }

    companion object {}
}