package com.example.findjob.model

import com.google.gson.annotations.SerializedName

class Account {
    @SerializedName("username")
    var username:String
    @SerializedName("password")
    var password:String
    @SerializedName("phonenumber")
    var phonenumber:String
    @SerializedName("email")
    var email:String
    constructor(username:String , password:String, phonenumber:String , email:String){
        this.username = username
        this.password = password
        this.phonenumber = phonenumber
        this.email = email
    }

    var sUsername:String
        get() {return username}
        set(value) { username = value}

    var sPasswword:String
        get() {return password}
        set(value) { password = value}

    var sPhonenumber:String
        get() {return phonenumber}
        set(value) { phonenumber = value}

    var sEmail:String
        get() {return email}
        set(value) { email = value}

}