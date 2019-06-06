package com.example.mayurpancholi.chat_mvvm.remote.data2;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.mayurpancholi.chat_mvvm.remote.APICALL;
import com.example.mayurpancholi.chat_mvvm.remote.APICALL2;
import com.example.mayurpancholi.chat_mvvm.remote.VolleySingleton;
import com.example.mayurpancholi.chat_mvvm.remote.data.DataValues;
import com.example.mayurpancholi.chat_mvvm.viewmodel.allusermodel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by mayurpancholi on 06-06-2019.
 */

public class DataManager2 {

    private static final String TAG = "DataManager";

    // String name1;

    private Context context;
    private int userid2;


    public DataManager2(Context context) {

        this.context = context;
    }


    public void sendVolleyRequest1(final String token4, Context context, final DataValue dataValues) {


        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                APICALL2.BASEURL, new JSONObject(), new Response.Listener<JSONObject>() {


            @Override
            public void onResponse(JSONObject response) {
                dataValues.setJsonDataResponse1(response);
            }
        },
                new Response.ErrorListener()
                {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        dataValues.setVolleyError1(error);
                    }
                }

        ) {

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String,String>();
                headers.put("Authorization",token4);
                return headers;
            }

        };


        VolleySingleton.getInstance(context).addToRequestQueue(jsonObjectRequest);


    }

    void showData(String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();


    }


}
