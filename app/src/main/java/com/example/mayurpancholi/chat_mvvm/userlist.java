package com.example.mayurpancholi.chat_mvvm;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.android.volley.VolleyError;
import com.example.mayurpancholi.chat_mvvm.adapter.useradapter;
import com.example.mayurpancholi.chat_mvvm.remote.data2.DataManager2;
import com.example.mayurpancholi.chat_mvvm.remote.data2.DataValue;
import com.example.mayurpancholi.chat_mvvm.viewmodel.allusermodel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class userlist extends AppCompatActivity {

    private RecyclerView recyclerView;
    private useradapter customAdapter;
    private DataManager2 dataManger;
    private List<allusermodel> newsList;

    String token2;
    String URL1 = "https://chat.promactinfo.com/api/user";

    private int userid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userlist);

        SharedPreferences pref = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);

        token2 = pref.getString("sherdtoken", "");
        Log.e("token", token2);

        dataManger = new DataManager2(this);

        recyclerView = (RecyclerView) findViewById(R.id.recycle);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        newsList = new ArrayList<>();

        customAdapter = new useradapter(this, newsList);
        recyclerView.setAdapter(customAdapter);

        getdata();
    }

    public void getdata() {

       /* final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URL1, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);

                        allusermodel user = new allusermodel();
                        userid=jsonObject.getInt("id");
                        user.setId(jsonObject.getInt("id"));
                        user.setAll_user(jsonObject.getString("name"));


                        newsList.add(user);
                    } catch (JSONException e) {
                        e.printStackTrace();
                        progressDialog.dismiss();
                    }
                }
                customAdapter.notifyDataSetChanged();
                progressDialog.dismiss();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Volley", error.toString());
                progressDialog.dismiss();
            }
        })

        {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String,String>();
                headers.put("Authorization",token2);
                return headers;
            }

        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);

    }*/

        dataManger.sendVolleyRequest1(token2, userlist.this, new DataValue() {

            @Override
            public void setJsonDataResponse1(JSONArray response) {

                allusermodel userModel = new allusermodel();
                newsList = new ArrayList<>();


                try {




                    for (int i = 0; i < response.length(); i++) {

                        JSONObject jsonObject = response.getJSONObject(i);
                       // Log.e("final", String.valueOf(i));
                        userid = jsonObject.getInt("id");
                        userModel.setId(jsonObject.getInt("id"));
                        userModel.setAll_user(jsonObject.getString("name"));

                        newsList.add(userModel);
                    }


                } catch (JSONException jsonDataResponse) {
                    Log.e("error", String.valueOf(jsonDataResponse));

                }

                customAdapter.notifyDataSetChanged();


            }

            @Override
            public void setVolleyError1(VolleyError volleyError) {
                Log.e("Volley", volleyError.toString());
            }
        });


    }
}



