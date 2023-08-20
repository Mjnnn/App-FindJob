package com.example.findjob.model

class RatingCompany {
    var idrtcpn:Int
    var username:String
    var idcongty:Int
    var content:String
    var ratingcompany:Int
    var time:String
    var imgavata:String
    constructor(idrtcpn:Int,username:String,idcongty:Int,content:String,ratingcompany:Int,time:String,imgavata:String){
        this.idrtcpn = idrtcpn
        this.username = username
        this.idcongty = idcongty
        this.content = content
        this.ratingcompany = ratingcompany
        this.time = time
        this.imgavata = imgavata
    }
}