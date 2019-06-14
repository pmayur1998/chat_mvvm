package com.example.mayurpancholi.chat_mvvm;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.mayurpancholi.chat_mvvm.adapter.messageadapter;
import com.example.mayurpancholi.chat_mvvm.adapter.useradapter;
import com.example.mayurpancholi.chat_mvvm.databinding.ActivityMessagelistBinding;
import com.example.mayurpancholi.chat_mvvm.interfaces.Post;
import com.example.mayurpancholi.chat_mvvm.messagelist_remote.data.messagelist_datamanager;


import com.example.mayurpancholi.chat_mvvm.messagelist_remote.data.messagelist_datavalue;

import com.example.mayurpancholi.chat_mvvm.postmessage_remote.data.postmessage_datamanager;
import com.example.mayurpancholi.chat_mvvm.postmessage_remote.data.postmessage_datavalue;
import com.example.mayurpancholi.chat_mvvm.realm.Model.offlinemsg;
import com.example.mayurpancholi.chat_mvvm.viewmodel.Postmessagemodel;
import com.example.mayurpancholi.chat_mvvm.viewmodel.messagemodel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import io.realm.Realm;
import io.realm.RealmResults;

public class messagelist extends AppCompatActivity {

    private static final String TAG = "messagelist";

    private RecyclerView recyclerView;
    private ActivityMessagelistBinding activityMessagelistBinding;
    private Postmessagemodel postmessagemodel;

    private LinearLayoutManager linearLayoutManager;
    private DividerItemDecoration dividerItemDecoration;
    private RecyclerView.Adapter customAdapter;
    private messagelist_datamanager dataManger;
    private postmessage_datamanager postmessage_datamanager;
    private List<messagemodel> newsList;
    private offlinemsg off;


    final Handler handler = new Handler();
    Timer timer1;
    TimerTask dotask1;
    String tokens;
    String sendmsg;
    Realm realm;
    String db_msg, db_auto,db_msg1, db_auto1;
    int db_touserid,db_touserid1;

    int toid;
    private TimerTask doTask;
    private Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_messagelist);


        toid = getIntent().getExtras().getInt("clickid");
        Log.e("toid", String.valueOf(toid));

        SharedPreferences pref = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);

        tokens = pref.getString("sherdtoken", "");

        realm = Realm.getDefaultInstance();


        activityMessagelistBinding = DataBindingUtil.setContentView(this, R.layout.activity_messagelist);


        recyclerView = (RecyclerView) findViewById(R.id.recycle1);
        newsList = new ArrayList<>();
        customAdapter = new messageadapter(getApplicationContext(),newsList);

        linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), linearLayoutManager.getOrientation());

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setAdapter(customAdapter);


        postmessagemodel = new Postmessagemodel();
        postmessage_datamanager = new postmessage_datamanager(this);

        activityMessagelistBinding.setPostmsg(postmessagemodel);


        dataManger = new messagelist_datamanager(this);

        checkofflinedata();

        activityMessagelistBinding.setPostbtn(new Post() {
            @Override
            public void onclick() {
                sendmsg = postmessagemodel.getMSG();
                Log.e("token", tokens + "message" + postmessagemodel.getMSG());

                if (isNetworkAvailable() && sendmsg != null) {
                    postmessage(sendmsg, toid, tokens);


                } else {
                    writedb(sendmsg.toString(), tokens.toString(), toid);
                }


            }
        });


        getmessage();

        timer = new Timer();


        doTask = new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @SuppressLint("WrongConstant")
                    @SuppressWarnings("unchecked")
                    public void run() {
                        try {

                            if (isNetworkAvailable()) {

                                Log.e("connected", String.valueOf(true));
                                if(isNetworkAvailable()){

                                    checkofflinedata();
                                }

                            } else {
                                Toast.makeText(getApplicationContext(), "wait for app goes online to get message after refresh", 5000).show();
                            }


                            Intent intent = getIntent();
                            finish();
                            startActivity(intent);

                        } catch (Exception e) {
                            // TODO Auto-generated catch block
                        }
                    }
                });
            }
        };

        timer.schedule(doTask, 20000);


    }

    private void checkofflinedata()

    {
        RealmResults<offlinemsg> allofflinemsg = realm.where(offlinemsg.class).findAll();


        if(allofflinemsg != null)
        {
            for (offlinemsg f1 : allofflinemsg) {
                db_msg = f1.getOfflinemsg_msg();
                db_auto = f1.getOfflinemsg_auth();
                db_touserid = f1.getOfflinemsg_touserid();
                Log.e("iddd", String.valueOf(f1.getOfflinemsg_id()));

                offlinepostmessage(db_msg, db_touserid, db_auto);

            }

        }

    }

    @Override
    protected void onStart() {
        super.onStart();


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        timer.cancel();
        doTask.cancel();

        Intent intent = new Intent(messagelist.this, userlist.class);
        startActivity(intent);
        messagelist.this.finish();

    }




    private void writedb(final String sendmsg, final String tokens, final int toid)
    {

        realm.executeTransactionAsync(new Realm.Transaction()
        {
            @Override
            public void execute(Realm bgRealm) {

                Number nextid = bgRealm.where(offlinemsg.class).max("offlinemsg_id");

                int newkey = (nextid == null) ? 1 : nextid.intValue()+1;
                offlinemsg off_msg = bgRealm.createObject(offlinemsg.class,newkey);

                Log.e("id",String.valueOf(off_msg.getOfflinemsg_id()));
                off_msg.setOfflinemsg_msg(sendmsg);
                off_msg.setOfflinemsg_auth(tokens);
                off_msg.setOfflinemsg_touserid(toid);

            }

        },
                new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess()
            {
                Log.v("Database", "data enterd");
            }
        },
                new Realm.Transaction.OnError()
                {
            @Override
            public void onError(Throwable error) {
                Log.e("error", error.getMessage());
            }
        });



    }


    private void deleteofflinedata(final String db_mssg)
    {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                try{
                    RealmResults<offlinemsg> result = realm.where(offlinemsg.class).findAll();
                    offlinemsg off = result.where().equalTo("Offlinemsg_msg",db_mssg).findFirst();

                    if(off!=null)
                    {

                        if (!realm.isInTransaction())
                        {
                            realm.beginTransaction();
                        }
                        off.deleteFromRealm();
                       // realm.commitTransaction();
                    }
                    else
                    {
                        Log.e("show",String.valueOf(true));
                    }

                }
                catch (Exception e)
                {

                }

            }
        });
    }



    private boolean isNetworkAvailable() //check app is online or not
    {
        ConnectivityManager manager =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        boolean isAvailable = false;
        if (networkInfo != null && networkInfo.isConnected()) {
            // Network is present and connected
            isAvailable = true;
        }
        return isAvailable;
    }


    public void getmessage() {
        dataManger.sendVolleyRequest2(tokens, toid, messagelist.this, new messagelist_datavalue() {

            @Override
            public void setJsonDataResponse(JSONArray response) {


                try {




                    for (int i = 0; i < response.length(); i++) {

                        messagemodel userModel = new messagemodel();

                        JSONObject jsonObject = response.getJSONObject(i);
                        // Log.e("final", String.valueOf(i));
                        userModel.setFromUserId(jsonObject.getInt("fromUserId"));
                        userModel.setMessage(jsonObject.getString("message"));
                        userModel.setToUserId(jsonObject.getInt("toUserId"));
                        Log.e("message", userModel.getMessage());
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


    private void postmessage(String post_msg, int post_toid, String post_auth) {

        postmessage_datamanager.sendVolleyRequest3(post_msg, post_toid, post_auth, messagelist.this, new postmessage_datavalue() {
            @Override
            public void setJsonDataResponse(JSONObject response) {
            }

            @Override
            public void setVolleyError(VolleyError volleyError) {

            }
        });

    }


    private void offlinepostmessage(String post_msg1, int post_toid1, String post_auth1) {

        postmessage_datamanager.sendVolleyRequest3(post_msg1, post_toid1, post_auth1, messagelist.this, new postmessage_datavalue() {
            @Override
            public void setJsonDataResponse(JSONObject response) {
            }

            @Override
            public void setVolleyError(VolleyError volleyError) {

            }
        });

        deleteofflinedata(post_msg1);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }

}

