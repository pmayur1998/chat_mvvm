package com.example.mayurpancholi.chat_mvvm.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.widget.Toast;

import com.example.mayurpancholi.chat_mvvm.BR;
import com.example.mayurpancholi.chat_mvvm.model.User;

import java.util.regex.Pattern;

/**
 * Created by mayurpancholi on 05-06-2019.
 */

public class LoginViewModel extends BaseObservable{

    public String name;

    public LoginViewModel(User user) {

        this.name = user.name;

    }

    public LoginViewModel() {
    }

    @Bindable

    public String getName() {
        if(name == null)
            return "";
        else
            return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
    }


    public boolean isValidName(String name)
    {
        if(name.contains(" "))
        {
            return false;
        }
        return true;

    }


    public boolean isEmptyName(String name)
    {
        if(name.equals(""))
        {
            return false;
        }
        return true;
    }



}
