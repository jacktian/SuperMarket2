package com.wuyin.supermarket.model;

import android.graphics.drawable.Drawable;

/**
 * Created by yinlong on 2016/5/11.
 */
public class UserInfo {

    private String name;
    private String url;
    private String email;

    public String getIcon() {
        return url;
    }

    public void setIcon(String url) {
        this.url = url;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserInfo(String name, String url, String email) {
        this.name = name;
        this.url = url;
        this.email = email;
    }
}
