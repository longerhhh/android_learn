package com.example.test.tst

open class KA {
    constructor() {
        println("constructor")
    }
    init {
        println("KA")
    }
}

class KB:KA() {
    init {
        println("KB")
    }
}

fun main() {
    KB()
}