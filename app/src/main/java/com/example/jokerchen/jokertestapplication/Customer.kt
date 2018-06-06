package com.example.jokerchen.jokertestapplication

import kotlin.properties.Delegates

/**
 * Created by jokerchen on 2018/5/9.
 */
data class Customer(var name:String,val email:String) {
    val a:Int = 1
    var name1: String by Delegates.observable("") {
        prop,old,new ->
        println()
    }

    inner class Nest{
        val b = a
    }
}
