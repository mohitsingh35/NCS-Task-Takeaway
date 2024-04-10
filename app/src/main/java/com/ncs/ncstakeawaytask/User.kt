package com.ncs.ncstakeawaytask

data class User(
    val email:String,
    val password:String,
    val name:String,
    val bio:String,
    val phoneNum:String,
    val detailsAdded:Boolean
)
