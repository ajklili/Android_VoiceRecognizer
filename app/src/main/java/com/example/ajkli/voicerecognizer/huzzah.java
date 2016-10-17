package com.example.ajkli.voicerecognizer;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by ajkli on 10/14/2016.
 */

public class huzzah {
    public static String deliverToDevice(String ip,String contents)
    {
        String msg = "Success";
        try{
            String POST_URL = "http://"+ip;
            URL url=new URL(POST_URL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            //设置运行输入,输出:
            conn.setDoOutput(true);
            conn.setDoInput(true);
            //设置请求方式
            conn.setRequestMethod("POST");
            //Post方式不能缓存,需手动设置为false
            conn.setUseCaches(false);
            conn.setInstanceFollowRedirects(true);
            conn.setRequestProperty("Content-Type","application/json");
            conn.connect();
            DataOutputStream out=new DataOutputStream(conn.getOutputStream());
            // 向对象输出流写出数据，这些数据将存到内存缓冲区中
            out.writeBytes(contents);
            // 刷新对象输出流，将任何字节都写入潜在的流中
            out.flush();
            // 关闭流对象。此时，不能再向对象输出流写入任何数据，先前写入的数据存在于内存缓冲区中
            out.close();

            BufferedReader reader=new BufferedReader(new InputStreamReader(conn.getInputStream()));

            String lines;
            StringBuilder sb =new StringBuilder("");
            while((lines=reader.readLine())!=null){
                lines=new String(lines.getBytes(),"utf-8");
                sb.append(lines);
            }
            reader.close();
            msg=sb.toString();
            return msg;
        }catch(Exception e){e.printStackTrace();}
        return msg;
    }
}
