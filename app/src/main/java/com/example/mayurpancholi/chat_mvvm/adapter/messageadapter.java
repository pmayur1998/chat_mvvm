package com.example.mayurpancholi.chat_mvvm.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mayurpancholi.chat_mvvm.R;
import com.example.mayurpancholi.chat_mvvm.databinding.Entrys;
import com.example.mayurpancholi.chat_mvvm.databinding.MessageBinding;
import com.example.mayurpancholi.chat_mvvm.interfaces.Post;
import com.example.mayurpancholi.chat_mvvm.interfaces.Presenters2;
import com.example.mayurpancholi.chat_mvvm.messagelist;
import com.example.mayurpancholi.chat_mvvm.viewmodel.allusermodel;
import com.example.mayurpancholi.chat_mvvm.viewmodel.messagemodel;

import java.util.List;

/**
 * Created by mayurpancholi on 07-06-2019.
 */

public class messageadapter extends RecyclerView.Adapter<messageadapter.CustomView1_msg> {


    List<messagemodel> list;
    private Context context;
    private LayoutInflater layoutInflater;


    public messageadapter(Context context,List<messagemodel> list)
    {
        this.context =context;
        this.list = list;
    }

    public messageadapter() {

    }

    @Override
    public CustomView1_msg onCreateViewHolder(ViewGroup parent, int viewType) {
        if(layoutInflater == null)
        {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }

        final MessageBinding messageBinding  = MessageBinding.inflate(layoutInflater,parent,false);


        //  View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.innerlayout,parent,false);
        return new CustomView1_msg(messageBinding);
    }

    @Override
    public void onBindViewHolder(CustomView1_msg holder, int position) {
        messagemodel newsModel = list.get(position);

        holder.bind(newsModel);
       // holder.title.setGravity(Gravity.RIGHT);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class CustomView1_msg extends RecyclerView.ViewHolder
    {
        private MessageBinding messageBinding;
        // TextView title, desc;
        public CustomView1_msg(MessageBinding messageBinding)
        {
            super(messageBinding.getRoot());

            this.messageBinding = messageBinding;
           // title = (TextView)itemView.findViewById(R.id.titleval);
            //desc =(TextView)itemView.findViewById(R.id.descval);

        }

        public void bind(messagemodel messagemodel)
        {
            this.messageBinding.setMessagelist(messagemodel);


        }

        public MessageBinding getNewsBinding()
        {
            return messageBinding;
        }
    }



}
