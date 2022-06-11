package com.example.myapplication.test;

import java.io.File;
import java.io.FileNotFoundException;

import okio.Okio;

/**
 * FileName: TestOkio
 * create date: 2022/5/18
 *
 * @author: longer
 * @description: okio test
 */
public class TestOkio {
    public static void main(String[] args) {
        try {
            Okio.sink(new File("test.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
