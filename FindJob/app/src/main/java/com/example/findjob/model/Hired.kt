package com.example.findjob.model

class Hired {
    var idcongviec:Int
    var idcongty:Int
    var username:String
    var nganhnghe:String
    var theloai:String
    var chucdanh:String
    var soluong:Int
    var mucluong:String
    var ngaydangky:String ?= null
    var ngayketthuc:String
    var luyy:String
    var imgcongviec:String
    var trangthai:String ?= null
    var diachi:String
    var tinh_tp:String
    constructor(idcongty:Int,idcongviec:Int,username:String,imgcongviec:String, nganhnghe:String, theloai:String,chucdanh:String,mucluong:String,soluong:Int,diachi:String,tinh_tp:String,luyy:String,ngayketthuc:String){
        this.tinh_tp = tinh_tp
        this.idcongviec = idcongviec
        this.idcongty = idcongty
        this.username = username
        this.imgcongviec = imgcongviec
        this.nganhnghe = nganhnghe
        this.theloai = theloai
        this.chucdanh = chucdanh
        this.mucluong =mucluong
        this.soluong = soluong
        this.diachi = diachi
        this.luyy = luyy
        this.ngayketthuc = ngayketthuc
    }
}