package com.example.findjob.model

class NotificationAdmin {
    var idnotify:Int
    var usernamead:String
    var username:String
    var idtopic:Int
    var topic:String
    var trangthai:String
    var time:String
    var imgavata:String
    constructor(idnotify:Int,usernamead:String,username:String,idtopoc:Int,topic:String,trangthai:String,time:String,imgavata:String){
        this.idnotify = idnotify
        this.usernamead = usernamead
        this.username = username
        this.idtopic = idtopoc
        this.topic = topic
        this.trangthai = trangthai
        this.time = time
        this.imgavata = imgavata
    }
}