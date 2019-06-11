package com.example.mayurpancholi.chat_mvvm.viewmodel;

import android.databinding.BaseObservable;

import com.example.mayurpancholi.chat_mvvm.model.Msg;

/**
 * Created by mayurpancholi on 07-06-2019.
 */

public class messagemodel  extends BaseObservable {

    public String message;
    public int fromUserId;
    public int toUserId;

    public messagemodel()
    {

    }

    public messagemodel(Msg msg) {
        this.message = msg.message;
        this.fromUserId = msg.fromUserId;
        this.toUserId = msg.toUserId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(int fromUserId) {
        this.fromUserId = fromUserId;
    }

    public int getToUserId() {
        return toUserId;
    }

    public void setToUserId(int toUserId) {
        this.toUserId = toUserId;
    }
}

