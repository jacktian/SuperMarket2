package com.wuyin.supermarket.httpresult;

import com.wuyin.supermarket.holder.base.BaseHolder;
import com.wuyin.supermarket.httpresult.base.BaseHttpRequest;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wuyin on 2016/5/28.
 */
public class TopHttpRequest extends BaseHttpRequest<List<String>> {
    @Override
    public List<String> parseJson(String results) {
        List<String> datas = new ArrayList<>();
        try {
            JSONArray array = new JSONArray(results);
            for (int i = 0; i < array.length(); i++) {
                String str = array.getString(i);
                datas.add(str);
            }
            return datas;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public String getKey() {
        return "hot";
    }
}
