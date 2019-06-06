package com.example.mayurpancholi.chat_mvvm.remote.data;

import com.android.volley.VolleyError;

import org.json.JSONObject;

/**
 * Created by mayurpancholi on 06-06-2019.
 */

public interface DataValues {

    public void setJsonDataResponse(JSONObject response);
    public void setVolleyError(VolleyError volleyError);
}
