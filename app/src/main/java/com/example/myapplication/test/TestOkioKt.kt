package com.example.myapplication.test

import okio.Buffer
import okio.buffer
import okio.sink
import okio.source
import java.io.File
import java.nio.charset.Charset

/**
 * FileName: TestOkioKt
 * create date: 2022/5/18
 *
 * @author: longer
 * @description: okio test
 */
fun main() {
    val file = File("in.txt")
//    file.sink().write(Buffer().writeInt(100), 2)
    val buffer = file.source().buffer()
    println(buffer.readUtf8Line())
    println(buffer.readUtf8Line())
    println(buffer.readUtf8Line())
    buffer.close()
    
    val buffer1 = file.sink(true).buffer()
    buffer1.writeString("whversyk\n 王者荣耀", Charset.defaultCharset())
    buffer1.close()
    
    val bufferedReader = file.inputStream().bufferedReader()
    bufferedReader.readLine()
    bufferedReader.close()
    
    val bufferedWriter = file.outputStream().bufferedWriter()
    bufferedWriter.write("something")
    bufferedWriter.append("something")
    bufferedWriter.close()
}