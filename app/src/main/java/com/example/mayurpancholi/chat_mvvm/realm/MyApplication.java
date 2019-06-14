package com.example.mayurpancholi.chat_mvvm.realm;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;


/**
 * Created by mayurpancholi on 12-06-2019.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        RealmConfiguration configuration = new RealmConfiguration.Builder().name("realmdata.realm").build();
        Realm.setDefaultConfiguration(configuration);

    }
}
