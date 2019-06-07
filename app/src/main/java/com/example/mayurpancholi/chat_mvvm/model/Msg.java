package com.example.mayurpancholi.chat_mvvm.model;

/**
 * Created by mayurpancholi on 07-06-2019.
 */

public class Msg {
    public String message;
    public int fromUserId;
    public int toUserId;



    public Msg(String Message, int FromUserId, int ToUserId) {
        message = Message;
        fromUserId = FromUserId;
        toUserId = ToUserId;
    }

    public Msg()
    {

    }

}
