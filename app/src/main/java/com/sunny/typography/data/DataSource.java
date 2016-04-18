package com.sunny.typography.data;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by hzsunyj on 16/4/15.
 */
public class DataSource {

    private static String json = "{\"data\":[[\"显示内容:\",\"显示内容1的描述\"],[\"显示内容:\",\"显示内容2的描述\"],[\"显示内容:\"," +
            "\"显示内容3的描述\"]," +
            "[\"显示内容:\",\"显示内容4的描述\"],[\"样例:\",\"显示内容5的描述，但是我需要显示的内容很长很长，不止一行内容\"],[\"显示内容:\"," +
            "\"显示内容6的描述，但是我需要显示的内容很长很长，不止一行内容\"]]}";

    public static JSONArray getArray() {
        try {
            JSONObject object = new JSONObject(json);
            return object.getJSONArray("data");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
