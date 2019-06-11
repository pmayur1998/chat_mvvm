package com.example.mayurpancholi.chat_mvvm.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.example.mayurpancholi.chat_mvvm.BR;
import com.example.mayurpancholi.chat_mvvm.model.Postmessage;
import com.example.mayurpancholi.chat_mvvm.model.User;

/**
 * Created by mayurpancholi on 07-06-2019.
 */

public class Postmessagemodel  extends BaseObservable {
    public String MSG;

    public Postmessagemodel(Postmessage p) {

        this.MSG = p.postmessage;

    }

    public Postmessagemodel() {
    }

    public String getMSG() {
        return MSG;
    }

    public void setMSG(String MSG) {
        this.MSG = MSG;
    }
}
