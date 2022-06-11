package com.example.pmannotionhandler.annotion

import java.lang.annotation.ElementType

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FUNCTION)
annotation class RequestPermission(val pm:String)
