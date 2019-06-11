package com.example.mayurpancholi.chat_mvvm.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.databinding.BaseObservable;

import com.example.mayurpancholi.chat_mvvm.model.allusers;

/**
 * Created by mayurpancholi on 06-06-2019.
 */

public class allusermodel extends BaseObservable {


    public int id;
    public String all_user;


    public allusermodel() {
    }

    public allusermodel(int id,String name ) {


        this.id = id;
        this.all_user = name;


    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAll_user() {
        return all_user;
    }

    public void setAll_user(String all_user) {
        this.all_user = all_user;
    }

}


