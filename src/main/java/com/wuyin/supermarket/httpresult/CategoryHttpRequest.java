package com.wuyin.supermarket.httpresult;

import com.wuyin.supermarket.httpresult.base.BaseHttpRequest;
import com.wuyin.supermarket.model.CategoryInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wuyin on 2016/5/26.
 */
public class CategoryHttpRequest extends BaseHttpRequest<List<CategoryInfo>> {
    @Override
    public List<CategoryInfo> parseJson(String results) {

        List<CategoryInfo> infos = new ArrayList<>();

        try {
            JSONArray array = new JSONArray(results);
            for (int i = 0; i < array.length(); i++) {
                JSONObject jsonObject = array.getJSONObject(i);
                String title = jsonObject.getString("title");
                CategoryInfo categoryInfo = new CategoryInfo();
                categoryInfo.setTitle(title);
                categoryInfo.setIsTitle(true);
                infos.add(categoryInfo);
                JSONArray jsonArray = jsonObject.getJSONArray("infos");
                for (int j = 0; j < jsonArray.length(); j++) {
                    JSONObject object = jsonArray.getJSONObject(j);
                    String url1 = object.getString("url1");
                    String url2 = object.getString("url2");
                    String url3 = object.getString("url3");

                    String name1 = object.getString("name1");
                    String name2 = object.getString("name2");
                    String name3 = object.getString("name3");
                    CategoryInfo categoryInfo1 = new CategoryInfo(title, url1, url2, url3, name1, name2, name3, false);

                    infos.add(categoryInfo1);
                }
            }
            return infos;
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public String getKey() {
        return "category";
    }
}
