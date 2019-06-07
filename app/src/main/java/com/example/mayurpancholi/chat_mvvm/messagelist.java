package com.example.mayurpancholi.chat_mvvm;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.android.volley.VolleyError;
import com.example.mayurpancholi.chat_mvvm.adapter.messageadapter;
import com.example.mayurpancholi.chat_mvvm.adapter.useradapter;
import com.example.mayurpancholi.chat_mvvm.databinding.ActivityMainBinding;
import com.example.mayurpancholi.chat_mvvm.interfaces.Post;
import com.example.mayurpancholi.chat_mvvm.messagelist_remote.data.messagelist_datamanager;


import com.example.mayurpancholi.chat_mvvm.messagelist_remote.data.messagelist_datavalue;

import com.example.mayurpancholi.chat_mvvm.postmessage_remote.data.postmessage_datamanager;
import com.example.mayurpancholi.chat_mvvm.postmessage_remote.data.postmessage_datavalue;
import com.example.mayurpancholi.chat_mvvm.remote.data.DataValues;
import com.example.mayurpancholi.chat_mvvm.viewmodel.Postmessagemodel;
import com.example.mayurpancholi.chat_mvvm.viewmodel.messagemodel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class messagelist extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ActivityMainBinding activityMainBinding1;
    private Postmessagemodel postmessagemodel;
    private messageadapter customAdapter;
    private messagelist_datamanager dataManger;
    private postmessage_datamanager postmessage_datamanager;
    private List<messagemodel> newsList;

    String tokens;
    String sendmsg;

    private int toid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_messagelist);

        toid=getIntent().getExtras().getInt("clickid");

        SharedPreferences pref = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);

        tokens = pref.getString("sherdtoken", "");
        Log.e("token", tokens);

        activityMainBinding1 = DataBindingUtil.setContentView(this, R.layout.activity_messagelist);

        postmessagemodel = new Postmessagemodel();
        postmessage_datamanager = new postmessage_datamanager(this);

        activityMainBinding1.setPostmsg(postmessagemodel);
        activityMainBinding1.setPostbtn(new Post() {
            @Override
            public void onclick() {
                postmessage();
            }
        });

        sendmsg = postmessagemodel.getMSG();
        dataManger = new messagelist_datamanager(this);

        recyclerView = (RecyclerView) findViewById(R.id.recycle1);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        newsList = new ArrayList<>();

        customAdapter = new messageadapter(this, newsList);
        recyclerView.setAdapter(customAdapter);

        getmessage();

    }



    public void getmessage()
    {
        dataManger.sendVolleyRequest2(tokens,toid,messagelist.this, new messagelist_datavalue() {

            @Override
            public void setJsonDataResponse(JSONArray response) {

                messagemodel userModel = new messagemodel();
                newsList = new ArrayList<>();


                try {




                    for (int i = 0; i < response.length(); i++) {

                        JSONObject jsonObject = response.getJSONObject(i);
                        // Log.e("final", String.valueOf(i));
                        userModel.setFromUserId(jsonObject.getInt("fromUserId"));
                        userModel.setMessage(jsonObject.getString("message"));
                        userModel.setToUserId(jsonObject.getInt("toUserId"));

                        newsList.add(userModel);
                    }


                } catch (JSONException jsonDataResponse) {
                    Log.e("error", String.valueOf(jsonDataResponse));

                }

                customAdapter.notifyDataSetChanged();


            }

            @Override
            public void setVolleyError(VolleyError volleyError) {
                Log.e("Volley", volleyError.toString());
            }
        });


    }


    private void postmessage() {

        postmessage_datamanager.sendVolleyRequest3(sendmsg,toid,tokens,messagelist.this,new postmessage_datavalue() {
            @Override
            public void setJsonDataResponse(JSONObject response) {
                try {

                    Log.e("success",sendmsg);


                } catch (Exception e) {
                    Log.e("error", e.toString());
                }


            }

            @Override
            public void setVolleyError(VolleyError volleyError) {

            }
        });


    }

}

