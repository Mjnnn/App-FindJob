package com.example.findjob.controller

import android.media.Rating
import com.example.findjob.model.*
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import java.util.concurrent.TimeUnit
import retrofit2.Call as Call

interface ApiSever {
    companion object{
        // 192.168.1.13 - P311-5G
//        192.168.1.10
        var URL_SEVER = "http://192.168.1.14/DoAnCoSo2/"
        var gson = GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create()
        var okHttpClient = OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()
        var retrofit = Retrofit.Builder()
            .baseUrl(URL_SEVER)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiSever::class.java)
    }

    // HomeDetail
            // Hired and Hired Blog
    @GET("Android/GetHired.php")
    fun getHired_titlejob_location(@Query("nganhnghe") nganhnghe: String, @Query("tinh_tp") tinh_tp:String):Call<List<Hired>>
    @GET("Android/GetHired.php")
    fun getHired_titlejob(@Query("nganhnghe") nganhnghe:String):Call<List<Hired>>
    @GET("Android/GetHired.php")
    fun getHired_location(@Query("tinh_tp") tinh:String):Call<List<Hired>>
    @GET("Android/GetHired.php")
    fun getHired():Call<List<Hired>>
            // Blog
    @GET("Android/GetBlog.php")
    fun  getBlog():Call<List<Blog>>
            // Company
    @GET("Android/GetCompany.php")
    fun getCompany():Call<List<Company>>
            // RatingCompany
    @GET("Android/GetRatingJob.php")
    fun getRatingCompany(@Query("idcongty") idcongty:Int):Call<List<RatingCompany>>
    // Login
    @GET("Android/GetAccount.php")
    fun getDataLogin(@Query("username") username: String, @Query("password") password: String):Call<MyResponse>

    // Blog
    @GET("Android/GetTopicBlog.php")
    fun getTopicBlog(@Query("topicblog") topicblog:String):Call<List<Blog>>
        // RatingBlog
    @GET("Android/GetRatingJob.php")
    fun getRatingBlog(@Query("idblog") idblog:Int):Call<List<RatingBlog>>

    // SearchDetailBlog
    @GET("Android/GetBlog.php")
    fun  getSearchDetailBlog(@Query("titleblog") titleblog:String):Call<List<Blog>>

    // SearchDetailJob
    @GET("Android/GetHired.php")
    fun getSearchDetailJob(@Query("nganhnghe") nganhnghe:String):Call<List<Hired>>

    // ProfileDetail
    @GET("Android/GetAccount.php")
    fun getAccount(@Query("username") username: String):Call<List<Account>>
        // ListCompany
    @GET("Android/GetCompany.php")
    fun getListCompany(@Query("username") username: String):Call<List<Company>>
        // ListBlog
    @GET("Android/GetBlog.php")
    fun getListBlog(@Query("username") username: String):Call<List<Blog>>
        // ListJob
    @GET("Android/GetHired.php")
    fun getListJob(@Query("username") username: String):Call<List<Hired>>
        // FollowJob
    @GET("Android/GetFollowHired.php")
    fun getFollowJob(@Query("username") username: String):Call<List<Hired>>
        // FollowCompany
    @GET("Android/GetFollowCompany.php")
    fun getFollowCompany(@Query("username")  username: String):Call<List<Company>>
        // FollowBlog
    @GET("Android/GetFollowBlog.php")
    fun getFollowBlog(@Query("username") username: String):Call<List<Blog>>

    // JobDetail
        //RatingJob
    @GET("Android/GetRatingJob.php")
    fun getRatingJob(@Query("idcongviec") idcongviec:Int ):Call<List<RatingJob>>
    @FormUrlEncoded
    @POST("Android/PostRating.php")
    fun postRating(@Field("idtopic") idtopic:Int,
                      @Field("topic") topic:String,
                      @Field("username") username:String,
                      @Field("content") content:String):Call<MyResponse>

    // Notification
    @GET("Android/GetNotification.php")
    fun getNotification(@Query("username") username: String):Call<List<Notification>>

    // Notification Admin
    @GET("Android/GetNotificationAdmin.php")
    fun getNotificationAdmin(@Query("username") username: String):Call<List<NotificationAdmin>>

    // NotificationDetailTopic
        @GET("Android/GetNotificationDetail.php")
        fun getNotificationJobDetail(@Query("idcongviec") idcongviec: Int):Call<List<Hired>>

        @GET("Android/GetNotificationDetail.php")
        fun  getNotificationBlogDetail(@Query("idblog") idblog: Int):Call<List<Blog>>

        @GET("Android/GetNotificationDetail.php")
        fun getNotificationCompanyDetail(@Query("idcongty") idcongty: Int):Call<List<Company>>

    @FormUrlEncoded
    @POST("Android/PostJob.php")
    fun postJob(@Field("username") username: String,
                @Field("idcongty") idcongty: Int,
                @Field("nganhnghe") nganhnghe: String,
                @Field("theloai") theloai:String,
                @Field("chucdanh") chucdanh:String,
                @Field("soluong") soluong:Int,
                @Field("mucluong") mucluong:String,
                @Field("ngaydangky") ngaydangky:String,
                @Field("ngayketthuc") ngayketthuc:String,
                @Field("luyy") luyy:String):Call<MyResponse>

//    INSERT INTO `hired` (`username`,`idcongty`,`nganhnghe`,`theloai`,`chucdanh`,`soluong`,`mucluong`,`ngaydangky`,`ngayketthuc`,`luyy`,`imgcongviec`,`trangthai`)



    // Tesst
    @GET("display2.php")
    fun getIdBlog2(@Query("idblog") id:Int):Call<List<Blog>>
    @GET("BlogPost.php")
    fun getIdBlog(@Query("id") id:Int): Call<Blog>
    @GET("Blog.php")
    fun getBlog1():Call<Blog>
    @GET("display.php")
    fun getAccount2(@Query("username") username:String):Call<List<Account>>
//    @GET("api/live")
//    suspend fun  getApi(): ResponseBody
//    fun getUrls(@Query("access_key") access_key:String,
//                @Query("currencies") currencies:String,
//                @Query("source") source:String,
//                @Query("format") format:Int):Call<News>
//   @Headers("Content-Type: application/json;charset=utf-8")
    @FormUrlEncoded
    @POST("display3.php")
    fun updateAccount(@Field("username") username:String,
               @Field("password") password:String,
               @Field("phonenumber") phonenumber:String,
               @Field("email") email:String ):Call<MyResponse>
}