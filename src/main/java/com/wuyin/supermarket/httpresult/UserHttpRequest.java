package com.wuyin.supermarket.httpresult;

import com.wuyin.supermarket.httpresult.base.BaseHttpRequest;
import com.wuyin.supermarket.model.UserInfo;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.jar.JarEntry;

/**
 * Created by yinlong on 2016/5/11.
 */
public class UserHttpRequest extends BaseHttpRequest<UserInfo> {
    UserInfo info = null;

    @Override
    public UserInfo parseJson(String results) {
        try {
            JSONObject jsonObject = new JSONObject(results);
            String name = jsonObject.getString("name");
            String url = jsonObject.getString("url");
            String email = jsonObject.getString("email");
            info = new UserInfo(name, url, email);
            return info;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public String getKey() {
        return "user";
    }
}
