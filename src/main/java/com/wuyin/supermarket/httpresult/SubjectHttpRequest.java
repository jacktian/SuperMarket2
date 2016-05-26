package com.wuyin.supermarket.httpresult;


import com.wuyin.supermarket.httpresult.base.BaseHttpRequest;
import com.wuyin.supermarket.model.SubjectInfo;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yinlong on 2016/5/5.
 */
public class SubjectHttpRequest extends BaseHttpRequest<List<SubjectInfo>> {


    @Override
    public List<SubjectInfo> parseJson(String results) {

        List<SubjectInfo> subjectInfos = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(results);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject result = jsonArray.getJSONObject(i);
                String des = result.getString("des");
                String url = result.getString("url");
                SubjectInfo subjectInfo = new SubjectInfo(des,url);
                subjectInfos.add(subjectInfo);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return subjectInfos;
    }

    @Override
    public String getKey() {
        return "subject";
    }
}
