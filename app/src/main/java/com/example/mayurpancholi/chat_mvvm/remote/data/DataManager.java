package com.example.mayurpancholi.chat_mvvm.remote.data;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.mayurpancholi.chat_mvvm.remote.APICALL;
import com.example.mayurpancholi.chat_mvvm.remote.VolleySingleton;
import com.example.mayurpancholi.chat_mvvm.viewmodel.LoginViewModel;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by mayurpancholi on 06-06-2019.
 */

public class DataManager {

  // String name1;

    private Context context;
    private LoginViewModel loginViewModel;

    public DataManager(Context context)
    {

        this.context = context;
    }




    public void sendVolleyRequest(Context context , final DataValues dataValues)
    {

        loginViewModel = new LoginViewModel();

        Map<String, String> jsonParams = new HashMap<String, String>();
        jsonParams.put("name",loginViewModel.getName().toString());

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                APICALL.BASEURL,
                new JSONObject(jsonParams),

                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        // showData(response.toString());

                        dataValues.setJsonDataResponse(response);


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        // showData(error.toString());
                        dataValues.setVolleyError(error);
                    }
                }

        )
        {

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String,String>();
                // headers.put("Content-Type", "application/json; charset=utf-8");
                return headers;
            }

            @Override
            public String getBodyContentType() {
                return "application/json";
            }
        };



        VolleySingleton.getInstance(context).addToRequestQueue(jsonObjectRequest);


    }
    void showData(String msg )
    {
        Toast.makeText(context,msg,Toast.LENGTH_LONG).show();


    }

}
