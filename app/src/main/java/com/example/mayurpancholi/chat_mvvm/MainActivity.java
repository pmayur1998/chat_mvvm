package com.example.mayurpancholi.chat_mvvm;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import com.android.volley.VolleyError;
import com.example.mayurpancholi.chat_mvvm.databinding.ActivityMainBinding;
import com.example.mayurpancholi.chat_mvvm.interfaces.Presenters;
import com.example.mayurpancholi.chat_mvvm.remote.data.DataManager;
import com.example.mayurpancholi.chat_mvvm.remote.data.DataValues;
import com.example.mayurpancholi.chat_mvvm.viewmodel.LoginViewModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    String token;
    Button btn;

    private ActivityMainBinding activityMainBinding;
    private DataManager dataManger;
    private LoginViewModel loginViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        loginViewModel = new LoginViewModel();

        dataManger = new DataManager(this);

        activityMainBinding.setUser(loginViewModel);
        activityMainBinding.setPresenter(new Presenters() {
            @Override
            public void clickLogin() {

                String name = loginViewModel.getName();
                Log.e("name",name);
                if (!loginViewModel.isEmptyName(name)) {

                    showToast("please enter name");
                } else if (!loginViewModel.isValidName(name)) {
                    showToast("Enter valid name");
                }

                else {

                    Log.e("name2",name);
                    SharedPreferences pref = getSharedPreferences("MyPrefs1", Context.MODE_PRIVATE);
                    SharedPreferences.Editor edit = pref.edit();

                    edit.remove("name");
                    edit.apply();

                    edit.putString("name", name);
                    edit.apply();

                    dataManger.sendVolleyRequest(name,MainActivity.this, new DataValues() {
                        @Override
                        public void setJsonDataResponse(JSONObject response) {
                            try {

                                token = response.getString("token");

                                showToast(token);

                                SharedPreferences pref = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
                                SharedPreferences.Editor edit = pref.edit();

                                edit.remove("sherdtoken");
                                edit.apply();

                                edit.putString("sherdtoken", token);
                                edit.apply();

                                 Intent intent = new Intent(MainActivity.this, userlist.class);
                                startActivity(intent);

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
        });

        btn = (Button)findViewById(R.id.button1);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, userlist.class);
                startActivity(intent);
            }
        });

    }

    private void showToast(String s) {

        Toast.makeText(this, s, Toast.LENGTH_LONG).show();

    }
}
