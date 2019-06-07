package com.example.mayurpancholi.chat_mvvm.messagelist_remote;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.mayurpancholi.chat_mvvm.remote.VolleySingleton2;

/**
 * Created by mayurpancholi on 07-06-2019.
 */

public class messagelistVolley {
    private static messagelistVolley instance1 ;
    private RequestQueue requestQueue1;
    private Context context1;


    private messagelistVolley(Context context)
    {
        this.context1 = context;
        requestQueue1 = getRequestQueue();

    }

    public static  synchronized messagelistVolley getInstance(Context context)
    {

        if(instance1 == null)
        {
            instance1 = new messagelistVolley(context);

        }
        return instance1;

    }




    public RequestQueue getRequestQueue()
    {

        if(requestQueue1 == null)
        {
            requestQueue1 = Volley.newRequestQueue(context1.getApplicationContext());
        }

        return requestQueue1;
    }



    public <T> void addToRequestQueue(Request<T> request)
    {

        getRequestQueue().add(request);
    }

}
