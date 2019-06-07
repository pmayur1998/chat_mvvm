package com.example.mayurpancholi.chat_mvvm.messagelist_remote.data;

import com.android.volley.VolleyError;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by mayurpancholi on 07-06-2019.
 */

public interface messagelist_datavalue {
    public void setJsonDataResponse(JSONArray response);
    public void setVolleyError(VolleyError volleyError);
}
