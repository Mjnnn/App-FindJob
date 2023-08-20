package com.example.findjob.model

class DataView {
    //All
    var idcongviec:Int ?= null
    var idcongty:Int ?= null
    var idblog:Int = 0
    var username:String = ""
    var diaChi:String ?= null
    var imgavata:Int = 0

    // hired
    var image:Int = 0
    var tenCongViec:String ?= null
    var theLoai:String?= null
    var chucdanh:String?=null
    var tinh:String?=null
    var soLuong:Int = 0
    var mucluong:String ?= null
    var ngayketthuc:String ?= null
    var luyy:String ?= null

    // constructor hired
    constructor(idcongviec:Int,idcongty:Int,username: String,image: Int,tenCongViec:String,theLoai:String,chucdanh:String,soLuong:Int,mucluong:String,diaChi:String,luyy:String,ngayketthuc:String){
        this.idcongviec = idcongviec
        this.idcongty = idcongty
        this.username = username
        this.image = image
        this.tenCongViec = tenCongViec
        this.theLoai = theLoai
        this.chucdanh = chucdanh
        this.soLuong = soLuong
        this.mucluong = mucluong
        this.diaChi = diaChi
        this.luyy = luyy
        this.ngayketthuc = ngayketthuc
    }

    // blog
    var titleblog:String = ""
    var contentblog:String = ""
    var imgblog:Int = 0
    var timeblog:String =""
    var topicblog:String =""
    var trangthai:String =""

    // constructor blog
    constructor(idblog:Int, username:String , titleblog:String , contentblog:String,imgblog:Int,timeblog:String,topicblog:String,trangthai:String){
        this.idblog = idblog
        this.username = username
        this.titleblog = titleblog
        this.contentblog = contentblog
        this.imgblog = imgblog
        this.timeblog = timeblog
        this.topicblog = topicblog
        this.trangthai = trangthai
    }

    // company
    var tencongty:String ?= null
    var loaihinhhd:String ?= null
    var trangthaicty:String ?= null
    var sonv:String ?= null
    var soluoccty:String ?= null
    var quocgia:String ?= null
    var tinh_tp:String ?= null
    var tennguoilh:String ?= null
    var sdt:String ?= null
    var email:String ?= null
    var imgcompany:Int = 0
    var imgnguoilh:Int ?= null
    var linkwebsite:String ?= null
    var phucloicty:String ?= null
    var masothue:String ?= null
    var trangthaichitiet:String ?= null
    var ngaydangky:String ?= null

    // constructor company
    constructor(idcongty:Int, username:String, tencongty:String, loaihinhhd:String, trangthaicty:String, sonv:String,soluoccty:String,
                quocgia:String,tinh_tp:String,diachi:String,tennguoilh:String,sdt:String,email:String,imgcompany:Int,imgnguoilh:Int,linkwebsite:String,
                phucloicty:String,masothue:String,trangthaichitiet:String,ngaydangky:String){
        this.idcongty = idcongty
        this.username = username
        this.tencongty = tencongty
        this.loaihinhhd = loaihinhhd
        this.trangthaicty = trangthaicty
        this.sonv = sonv
        this.soluoccty = soluoccty
        this.quocgia = quocgia
        this.tinh_tp = tinh_tp
        this.diaChi = diachi
        this.tennguoilh = tennguoilh
        this.sdt = sdt
        this.email = email
        this.imgcompany = imgcompany
        this.imgnguoilh = imgnguoilh
        this.linkwebsite = linkwebsite
        this.phucloicty = phucloicty
        this.masothue = masothue
        this.trangthaichitiet = trangthaichitiet
        this.ngaydangky = ngaydangky
    }

    // Rating Job
    var idrtjob:Int?=null
    var content:String?=null
    var ratingjob:Int?=null
    var time:String?=null

    //Constructor Rating Job
    constructor(idrtjob:Int , username:String, idcongviec:Int,content:String, ratingjob:Int,time:String,imgavata:Int){
        this.idrtjob = idrtjob
        this.username = username
        this.idcongviec = idcongviec
        this.content = content
        this.ratingjob = ratingjob
        this.time = time
        this.imgavata = imgavata
    }

    // Rating Blog
    var idcmt:Int ?= null
    var ratingblog:Int ?= null

    // Constructor Rating Blog
    constructor(idcmt:Int, username: String, idblog: Int,content: String,ratingblog:Int,imgavata: Int,time: String){
        this.idcmt = idcmt
        this.username = username
        this.idblog = idblog
        this.content = content
        this.ratingblog = ratingblog
        this.time = time
        this.imgavata = imgavata
    }

    //Rating Company
    var idrtcpn:Int?=null
    var ratingcompany:Int?=null
    constructor(idrtcpn:Int, username: String, idcongty: Int,imgavata: Int,content: String,ratingcompany:Int,time: String){
        this.idrtcpn = idrtcpn
        this.username = username
        this.idcongty = idcongty
        this.content = content
        this.ratingcompany = ratingcompany
        this.time = time
        this.imgavata = imgavata
    }

    // Notification
    var idnotify:Int?=null
    var usernametopic:String ?= null
    var idtopic:Int ?= null
    var topic:String ?= null
    constructor(idnotify:Int,usernametopic:String,username:String,idtopoc:Int,topic:String,time:String,imgavata:Int){
        this.idnotify = idnotify
        this.usernametopic = usernametopic
        this.username = username
        this.idtopic = idtopoc
        this.topic = topic
        this.time = time
        this.imgavata = imgavata
    }

    // Notification Admin
    var usernamead:String ?= null
    constructor(idnotify:Int,usernamead:String,username:String,idtopoc:Int,topic:String,trangthai:String,time:String,imgavata:Int){
        this.idnotify = idnotify
        this.usernamead = usernamead
        this.username = username
        this.idtopic = idtopoc
        this.topic = topic
        this.trangthai = trangthai
        this.time = time
        this.imgavata = imgavata
    }

    // CV
    var titlecv:String ?= null
    var imgcv:Int = 0
    constructor(titlecv:String,topic:String,time:String,imgcv:Int){
        this.titlecv = titlecv
        this.topic = topic
        this.time = time
        this.imgcv = imgcv
    }


}