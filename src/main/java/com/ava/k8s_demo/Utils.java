package com.ava.k8s_demo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.json.JsonArray;


public class Utils {
	public String getJson(String path) {
		//从给定位置获取文件
	    File file = new File(path);
	    BufferedReader reader = null;
	    //返回值,使用StringBuffer
	    StringBuffer data = new StringBuffer();
	    //
	    try {
	        reader = new BufferedReader(new FileReader(file));
	        //每次读取文件的缓存
	        String temp = null;
	        while((temp = reader.readLine()) != null){
	            data.append(temp);
	        }
	    } catch (FileNotFoundException e) {
	        e.printStackTrace();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }finally {
	        //关闭文件流
	        if (reader != null){
	            try {
	                reader.close();
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
	    }
	    return data.toString();
	}
	

}
