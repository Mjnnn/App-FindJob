package com.example.findjob.model

class Notification {
    var idnotify:Int
    var usernametopic:String
    var username:String
    var idtopic:Int
    var topic:String
    var time:String
    var imgavata:String
    constructor(idnotify:Int,usernametopic:String,username:String,idtopoc:Int,topic:String,time:String,imgavata:String){
        this.idnotify = idnotify
        this.usernametopic = usernametopic
        this.username = username
        this.idtopic = idtopoc
        this.topic = topic
        this.time = time
        this.imgavata = imgavata
    }
}