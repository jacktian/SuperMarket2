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
public class AppHttpRequest extends BaseHttpRequest<List<AppInfo>>{

    /**
     * 解析数据
     */
    @Override
    public List<AppInfo> parseJson(String results) {
        List<AppInfo> appInfos = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(results);
            for (int i = 0; i < jsonArray.length() ; i++){
                JSONObject result = jsonArray.getJSONObject(i);
                String id = result.getString("id");
                String name = result.getString("name");
                String packageName = result.getString("packageName");
                String iconUrl = result.getString("iconUrl");
                float stars = result.getLong("stars");
                long size = result.getLong("size");
                String downLoadUrl = result.getString("downloadUrl");
                String des = result.getString("des");
                AppInfo appInfo = new AppInfo(id,name,packageName,iconUrl,stars,size,downLoadUrl,des);
                appInfos.add(appInfo);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return appInfos;
    }

    /**
     * 地址的关键字
     * @return
     */
    @Override
    public String getKey() {
        return "app";
    }
}
