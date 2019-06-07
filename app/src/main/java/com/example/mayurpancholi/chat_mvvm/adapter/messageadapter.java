package com.example.mayurpancholi.chat_mvvm.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

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

public class messageadapter extends RecyclerView.Adapter<messageadapter.CustomView> {


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
    public messageadapter.CustomView onCreateViewHolder(final ViewGroup parent, final int viewType) {


        if(layoutInflater == null)
        {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }

        final MessageBinding newsBinding  = MessageBinding.inflate(layoutInflater,parent,false);


        //  View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.innerlayout,parent,false);
        return new CustomView(newsBinding);
    }

    @Override
    public void onBindViewHolder(messageadapter.CustomView holder, int position) {

        //  News news = newsList.get(position);
        // holder.title.setText(news.getTitle());
        // holder.desc.setText(news.getDesc());

        messagemodel newsModel = list.get(position);
        holder.bind(newsModel);




    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class CustomView extends RecyclerView.ViewHolder {

        private MessageBinding newsBinding;
        // TextView title, desc;
        public CustomView(MessageBinding newsBinding) {
            super(newsBinding.getRoot());

            this.newsBinding = newsBinding;
            //title = (TextView)itemView.findViewById(R.id.titleval);
            //desc =(TextView)itemView.findViewById(R.id.descval);

        }

        public void bind(messagemodel newsModel1)
        {
                this.newsBinding.setMessageList(newsModel1.getMessage());
        }

        public MessageBinding getNewsBinding()
        {
            return newsBinding;
        }

    }


}
