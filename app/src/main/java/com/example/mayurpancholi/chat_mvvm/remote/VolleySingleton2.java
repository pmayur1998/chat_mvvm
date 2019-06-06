package com.example.mayurpancholi.chat_mvvm.remote;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by mayurpancholi on 06-06-2019.
 */

public class VolleySingleton2 {

    private static VolleySingleton2 instance1 ;
    private RequestQueue requestQueue1;
    private Context context1;


    private VolleySingleton2(Context context)
    {
        this.context1 = context;
        requestQueue1 = getRequestQueue();

    }

    public static  synchronized VolleySingleton2 getInstance(Context context)
    {

        if(instance1 == null)
        {
            instance1 = new VolleySingleton2(context);

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
