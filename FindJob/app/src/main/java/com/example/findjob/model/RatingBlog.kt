package com.example.findjob.model

class RatingBlog {
    var idcmt:Int
    var username:String
    var idblog:Int
    var content:String
    var ratingblog:Int
    var time:String
    var imgavata:String
    constructor(idcmt:Int, username:String, idblog:Int, content:String, ratingblog:Int,time:String,imgavata:String){
        this.idcmt = idcmt
        this.username = username
        this.idblog = idblog
        this.content = content
        this.ratingblog = ratingblog
        this.time = time
        this.imgavata = imgavata
    }
}