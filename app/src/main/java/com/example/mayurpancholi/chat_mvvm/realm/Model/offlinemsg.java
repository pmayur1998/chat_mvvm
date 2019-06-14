package com.example.mayurpancholi.chat_mvvm.realm.Model;

import android.util.Log;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by mayurpancholi on 12-06-2019.
 */

public class offlinemsg extends RealmObject {

    @PrimaryKey
    private int offlinemsg_id;

    private String Offlinemsg_msg;
    private String Offlinemsg_auth;
    private int Offlinemsg_touserid;

    public int getOfflinemsg_id() {
        return offlinemsg_id;
    }

    public void setOfflinemsg_id(int offlinemsg_id) {
        this.offlinemsg_id = offlinemsg_id;
    }

    public String getOfflinemsg_msg() {
        return Offlinemsg_msg;
    }

    public void setOfflinemsg_msg(String offlinemsg_msg) {

        Offlinemsg_msg = offlinemsg_msg;
       // Log.e("Offlinemsg_msg",Offlinemsg_msg);
    }

    public String getOfflinemsg_auth() {
        return Offlinemsg_auth;
    }

    public void setOfflinemsg_auth(String offlinemsg_auth) {
        Offlinemsg_auth = offlinemsg_auth;
       // Log.e("Offlinemsg_auth",Offlinemsg_auth);
    }

    public int getOfflinemsg_touserid() {
        return Offlinemsg_touserid;
    }

    public void setOfflinemsg_touserid(int offlinemsg_touserid) {
        Offlinemsg_touserid = offlinemsg_touserid;
      // Log.e("Offlinemsg_touserid", String.valueOf(Offlinemsg_touserid));
    }
}
