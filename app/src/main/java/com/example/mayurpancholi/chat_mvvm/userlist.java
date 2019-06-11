package com.example.mayurpancholi.chat_mvvm;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.android.volley.VolleyError;
import com.example.mayurpancholi.chat_mvvm.adapter.useradapter;
//import com.example.mayurpancholi.chat_mvvm.model.allusers;
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
    private LinearLayoutManager linearLayoutManager;
    private DividerItemDecoration dividerItemDecoration;
    private useradapter customAdapter;
    private DataManager2 dataManger;
    private List<allusermodel> newsList;
    private RecyclerView.Adapter adapter;

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
        /*recyclerView.setLayoutManager(new LinearLayoutManager(this));
        newsList = new ArrayList<>();

        customAdapter = new useradapter(this, newsList);
        recyclerView.setAdapter(customAdapter);*/

        newsList = new ArrayList<>();
        adapter = new useradapter(getApplicationContext(),newsList);

        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), linearLayoutManager.getOrientation());

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setAdapter(adapter);

        getdata();
    }

    public void getdata() {



        dataManger.sendVolleyRequest1(token2, userlist.this, new DataValue() {

            @Override
            public void setJsonDataResponse1(JSONArray response) {

                allusermodel userModel = new allusermodel();
                newsList = new ArrayList<>();
                for (int i = 0; i < response.length(); i++) {

                    try {


                        JSONObject jsonObject = response.getJSONObject(i);
                       // Log.e("final", String.valueOf(i));
                        userid = jsonObject.getInt("id");
                        userModel.setId(jsonObject.getInt("id"));
                      //  Log.e("getid", String.valueOf(jsonObject.getInt("id")));
                        userModel.setAll_user(jsonObject.getString("name"));
                       // Log.e("getname", String.valueOf(jsonObject.getString("name")));

                        newsList.add(userModel);




                    } catch (JSONException jsonDataResponse) {
                        Log.e("error", String.valueOf(jsonDataResponse));

                    }
                }

                adapter.notifyDataSetChanged();


            }

            @Override
            public void setVolleyError1(VolleyError volleyError) {
                Log.e("Volley", volleyError.toString());
            }
        });


    }
}



