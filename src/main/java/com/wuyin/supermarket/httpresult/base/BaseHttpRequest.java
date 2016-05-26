package com.wuyin.supermarket.httpresult.base;

import android.os.SystemClock;

import com.squareup.okhttp.Request;
import com.wuyin.supermarket.manager.OkHttpManager;
import com.wuyin.supermarket.uri.Constants;
import com.wuyin.supermarket.utils.FileUtils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;

/**
 * Created by yinlong on 2016/5/5.
 */
public abstract class BaseHttpRequest<T> {

    private String results = "";

    public T load(int index) {
        loadServer(index);

        //加载数据的时候睡眠1s，真实演示
        SystemClock.sleep(1000);

        if (results != null) {
            return parseJson(results);
        } else {
            return null;
        }
    }

    /**
     * 解析json
     *
     * @param results
     * @return
     */
    public abstract T parseJson(String results);

    /**
     * 数据保存到本地
     *
     * @param json  json字符串
     * @param index 标记
     */
    //1.把整個json數據存儲到本地文件中


    //2、每條數據都取出來，保存到數據庫中
    private void saveLocal(String json, int index) {


        BufferedWriter bw = null;
        try {
            File dir = FileUtils.getCacheDir();
            File file = new File(dir, getKey() + "_" + index);//   /mnt/sdcard/googleplay/cache/home_index
            FileWriter fileWriter = new FileWriter(file);
            bw = new BufferedWriter(fileWriter);
            //在第一行寫一個過期時間
            //bw.write(System.currentTimeMillis() +1000*100 + "");
            bw.write(System.currentTimeMillis() + "");
            //換行
            bw.newLine();
            bw.write(json);
            bw.flush();
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }

    }

    /**
     * 从服务器端加载数据
     *
     * @param index 下标
     * @return
     */
    public String loadServer(int index) {
        OkHttpManager.getAsync(Constants.BASE_URL + getKey() + "?index=" + index + getParams(), new OkHttpManager.DataCallBack() {
            @Override
            public void requestFailure(Request request, IOException e) {

            }

            @Override
            public void requestSuccess(String result) throws Exception {

                results = result.toString();
            }
        });

        return results;
    }

    public String getParams() {
        return "";
    }

    /**
     * 从本地加载数据
     *
     * @return
     */
    private String loadLocal(int index) {
        //如果發現這個文件已經過期了，就不要去服複用緩存了
        File dir = FileUtils.getCacheDir();  //获取缓存文件夹
        File file = new File(dir, getKey() + "_" + index);
        try {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            long outOfDate = Long.parseLong(br.readLine());
            long currentTime = System.currentTimeMillis();
            //已经过期
            if (currentTime >= outOfDate) {
                return null;
            } else {  //没有过期
                String str = null;
                StringWriter sw = new StringWriter();
                while ((str = br.readLine()) != null) {
                    //写到内存中
                    sw.write(str);
                }
                return sw.toString();

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public abstract String getKey();

}
