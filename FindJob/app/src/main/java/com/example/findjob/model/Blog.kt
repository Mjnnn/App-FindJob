package com.example.findjob.model

class Blog {
    var idblog:Int
    var username:String
    var titleblog:String
    var contentblog:String
    var imgblog:String
    var timeblog:String
    var topicblog:String
    var trangthai:String
    constructor(idblog:Int, username:String , titleblog:String , contentblog:String,imgblog:String,timeblog:String,topicblog:String,trangthai:String){
        this.idblog = idblog
        this.username = username
        this.titleblog = titleblog
        this.contentblog = contentblog
        this.imgblog = imgblog
        this.timeblog = timeblog
        this.topicblog = topicblog
        this.trangthai = trangthai
    }
}