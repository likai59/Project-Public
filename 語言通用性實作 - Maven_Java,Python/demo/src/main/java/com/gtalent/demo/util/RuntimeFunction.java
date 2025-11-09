package com.gtalent.demo.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;

// 不使用 library
public class RuntimeFunction {
    public static void main(String[] args) {
        Process proc;
        try{
            proc = Runtime.getRuntime().exec("python C:\\Users\\likai\\OneDrive\\桌面\\IT之路\\Java\\Spring\\demo\\demo\\src\\main\\resources\\python\\RuntimeFunction.py");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }

            bufferedReader.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
