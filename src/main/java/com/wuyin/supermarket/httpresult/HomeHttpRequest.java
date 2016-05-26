package com.wuyin.supermarket.httpresult;

import com.wuyin.supermarket.httpresult.base.BaseHttpRequest;
import com.wuyin.supermarket.model.AppInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wuyin on 2016/5/2.
 */
public class HomeHttpRequest extends BaseHttpRequest<List<AppInfo>>{

    private List<String> pics = new ArrayList<>();

    /**
     * 解析json数据
     *
     *  private String id;
     private String name;
     private String packageName;
     private String iconUrl;
     private float stars;
     private long size;
     private String downloadUrl;
     private String des;
     */
    public List<AppInfo> parseJson(String json) {
        List<AppInfo> appInfos = new ArrayList<>();

        try {
            JSONObject jsonObject = new JSONObject(json);

            JSONArray picture = jsonObject.getJSONArray("picture");
            for (int i = 0 ; i < picture.length(); i++){
                String url = picture.getString(i);
                pics.add(url);
            }

            JSONArray jsonArray = jsonObject.getJSONArray("list");
            for (int i = 0 ; i < jsonArray.length() ; i ++){
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

    public List<String> getPics() {
        return pics;
    }

    public void setPics(List<String> pics) {
        this.pics = pics;
    }

    @Override
    public String getKey() {
        return "home";
    }

}
