package com.wuyin.supermarket.httpresult;

import com.wuyin.supermarket.httpresult.base.BaseHttpRequest;
import com.wuyin.supermarket.model.AppInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yinlong on 2016/5/11.
 */
public class DetailHttpRequest extends BaseHttpRequest<AppInfo> {

    String packageName;

    public DetailHttpRequest(String packageName) {
        this.packageName = packageName;
    }

    @Override
    public AppInfo parseJson(String results) {

        try {
            JSONObject result = new JSONObject(results);
            String id = result.getString("id");
            String name = result.getString("name");
            String packageName = result.getString("packageName");
            String iconUrl = result.getString("iconUrl");
            float stars = result.getLong("stars");
            long size = result.getLong("size");
            String downLoadUrl = result.getString("downloadUrl");
            String des = result.getString("des");

            String downloadNum = result.getString("downloadNum");
            String version = result.getString("version");
            String date = result.getString("date");
            String author = result.getString("author");

            List<String> screen = new ArrayList<>();
            JSONArray screenArray = result.getJSONArray("screen");
            for (int i = 0; i < screenArray.length(); i++) {
                screen.add(screenArray.getString(i));
            }

            List<String> safeUrl = new ArrayList<>();
            List<String> safeDesUrl = new ArrayList<>();
            List<String> safeDes = new ArrayList<>();
            List<Integer> safeDesColor = new ArrayList<>();
            JSONArray safeUrlArray = result.getJSONArray("safe");
            for (int i = 0; i < safeUrlArray.length(); i++) {
                JSONObject jsonObject = safeUrlArray.getJSONObject(i);
                safeUrl.add(jsonObject.getString("safeUrl"));
                safeDesUrl.add(jsonObject.getString("safeDesUrl"));
                safeDes.add(jsonObject.getString("safeDes"));
                safeDesColor.add(jsonObject.getInt("safeDesColor"));
            }

            AppInfo appInfo = new AppInfo(id, name, packageName, iconUrl, stars, size, downLoadUrl, des,
                    downloadNum, version, date, author, screen,
                    safeUrl, safeDesUrl, safeDes, safeDesColor);

            return appInfo;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String getKey() {
        return "detail";
    }

    /**
     * 额外带的参数
     *
     * @return
     */
    @Override
    public String getParams() {
        return "&packageName=" + packageName;
    }
}
