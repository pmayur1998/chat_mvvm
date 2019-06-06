package com.example.mayurpancholi.chat_mvvm.remote.data2;

import com.android.volley.VolleyError;

import org.json.JSONObject;

/**
 * Created by mayurpancholi on 06-06-2019.
 */

public interface DataValue {

    public void setJsonDataResponse1(JSONObject response);
    public void setVolleyError1(VolleyError volleyError);
}
