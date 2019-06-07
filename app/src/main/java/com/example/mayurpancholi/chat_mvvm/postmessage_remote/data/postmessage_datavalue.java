package com.example.mayurpancholi.chat_mvvm.postmessage_remote.data;

import com.android.volley.VolleyError;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by mayurpancholi on 07-06-2019.
 */

public interface postmessage_datavalue {
    public void setJsonDataResponse(JSONObject response);
    public void setVolleyError(VolleyError volleyError);
}
