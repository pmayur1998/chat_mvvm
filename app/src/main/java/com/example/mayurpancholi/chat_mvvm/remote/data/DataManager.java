package com.example.mayurpancholi.chat_mvvm.remote.data;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.mayurpancholi.chat_mvvm.remote.APICALL;
import com.example.mayurpancholi.chat_mvvm.remote.VolleySingleton;
import com.example.mayurpancholi.chat_mvvm.viewmodel.LoginViewModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by mayurpancholi on 06-06-2019.
 */

public class DataManager {

    private static final String TAG = "DataManager";

    // String name1;

    private Context context;


    public DataManager(Context context) {

        this.context = context;
    }


    public void sendVolleyRequest(final String name3, Context context, final DataValues dataValues) {


         Log.e( "sendVolleyRequest: ",name3 );

        JSONObject jsonParams = new JSONObject();
        //Map<String, String> jsonParams = new HashMap<String, String>();
        try {
            jsonParams.put("name", name3);
        } catch (JSONException j) {
            //Nothing
        }


        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                APICALL.BASEURL,
                jsonParams,

                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e(TAG, "onResponse: " + response);

                        // showData(response.toString());

                        dataValues.setJsonDataResponse(response);


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Log.e("hi",loginViewModel.getName());
                        Log.e("sendVolleyRequest2: ", APICALL.BASEURL + " Name : " + name3);
                        Log.e(TAG, "onErrorResponse: " + error.getLocalizedMessage());
                        // showData(error.toString());
                        dataValues.setVolleyError(error);
                    }
                }

        ) {

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                //headers.put("Content-Type", "application/json; charset=utf-8");
                return headers;
            }

            @Override
            public String getBodyContentType() {
                return "application/json";
            }
        };


        VolleySingleton.getInstance(context).addToRequestQueue(jsonObjectRequest);


    }

    void showData(String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();


    }

}
