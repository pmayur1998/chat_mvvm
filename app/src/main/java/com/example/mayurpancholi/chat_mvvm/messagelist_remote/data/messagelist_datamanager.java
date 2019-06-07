package com.example.mayurpancholi.chat_mvvm.messagelist_remote.data;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.mayurpancholi.chat_mvvm.messagelist_remote.messagelist_APICALL;
import com.example.mayurpancholi.chat_mvvm.remote.APICALL2;
import com.example.mayurpancholi.chat_mvvm.remote.VolleySingleton;
import com.example.mayurpancholi.chat_mvvm.remote.data2.DataValue;

import org.json.JSONArray;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by mayurpancholi on 07-06-2019.
 */

public class messagelist_datamanager {

    private static final String TAG = "messagelist_datamanager";

    // String name1;

    private Context context;



    public messagelist_datamanager(Context context) {

        this.context = context;
    }


    public void sendVolleyRequest2(final String usertoken,final int toid, Context context, final messagelist_datavalue dataValues) {


        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(messagelist_APICALL.BASEURL+toid, new Response.Listener<JSONArray>() {


            @Override
            public void onResponse(JSONArray response) {
                Log.e("done",usertoken);
                dataValues.setJsonDataResponse(response);
            }


        },
                new Response.ErrorListener()
                {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("tokennnn",usertoken);
                        dataValues.setVolleyError(error);
                    }
                }

        ) {

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String,String>();
                headers.put("Authorization",usertoken);
                return headers;
            }

            @Override
            public String getBodyContentType() {
                return "application/json";
            }

        };


        VolleySingleton.getInstance(context).addToRequestQueue(jsonArrayRequest);


    }

    void showData(String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();


    }

}
