package com.example.findjob.model

class RatingJob {
    var idrtjob:Int
    var username:String
    var idcongviec:Int
    var content:String
    var ratingjob:Int
    var time:String
    var imgavata:String
    constructor(idrtjob:Int , username:String, idcongviec:Int,content:String, ratingjob:Int,time:String,imgavata:String){
        this.idrtjob = idrtjob
        this.username = username
        this.idcongviec = idcongviec
        this.content = content
        this.ratingjob = ratingjob
        this.time = time
        this.imgavata = imgavata
    }
}