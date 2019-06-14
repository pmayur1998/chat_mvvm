package com.example.mayurpancholi.chat_mvvm.postmessage_remote.data;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.mayurpancholi.chat_mvvm.postmessage_remote.postmessage_APICALL;
import com.example.mayurpancholi.chat_mvvm.remote.APICALL;
import com.example.mayurpancholi.chat_mvvm.remote.VolleySingleton;
import com.example.mayurpancholi.chat_mvvm.remote.data.DataValues;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by mayurpancholi on 07-06-2019.
 */

public class postmessage_datamanager
{
    private static final String TAG = "Postmessage_DataManager";

    // String name1;

    private Context context;


    public postmessage_datamanager(Context context) {

        this.context = context;
    }




    public void sendVolleyRequest3(final String msg,final int touserid,final String token_, Context context, final postmessage_datavalue dataValues) {


         Log.e( "sendVolleyRequest1: ",msg);
        Log.e( "sendVolleyRequest2: ",token_);
        Log.e( "sendVolleyRequest3: ", String.valueOf(touserid));
        JSONObject jsonParams = new JSONObject();

        try {
            jsonParams.put("message", msg);
            jsonParams.put("toUserId",touserid);
        } catch (JSONException j) {
            //Nothing
        }


        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                postmessage_APICALL.BASEURL,
                jsonParams,null

               ,null


        ) {

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Authorization",token_);
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
