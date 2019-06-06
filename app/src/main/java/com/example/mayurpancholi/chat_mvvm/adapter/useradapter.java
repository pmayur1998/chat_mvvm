package com.example.mayurpancholi.chat_mvvm.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.mayurpancholi.chat_mvvm.databinding.Entrys;
import com.example.mayurpancholi.chat_mvvm.interfaces.Presenters2;
import com.example.mayurpancholi.chat_mvvm.viewmodel.allusermodel;

import java.util.List;

/**
 * Created by mayurpancholi on 06-06-2019.
 */

public class useradapter extends RecyclerView.Adapter<useradapter.CustomView> {

    List<allusermodel> list1;
    private Context context;
    private LayoutInflater layoutInflater;


    public useradapter(Context context,List<allusermodel> list1)
    {
        this.context =context;
        this.list1 = list1;
    }



    @Override
    public CustomView onCreateViewHolder(final ViewGroup parent, final int viewType) {


        if(layoutInflater == null)
        {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }

        final Entrys newsBinding  = Entrys.inflate(layoutInflater,parent,false);


        //  View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.innerlayout,parent,false);
        return new CustomView(newsBinding);
    }

    @Override
    public void onBindViewHolder(CustomView holder, int position) {

        //  News news = newsList.get(position);
        // holder.title.setText(news.getTitle());
        // holder.desc.setText(news.getDesc());

        allusermodel newsModel1 = list1.get(position);
        holder.bind(newsModel1);




    }

    @Override
    public int getItemCount() {
        return list1.size();
    }

    public class CustomView extends RecyclerView.ViewHolder {

        private Entrys newsBinding;
        // TextView title, desc;
        public CustomView(Entrys newsBinding) {
            super(newsBinding.getRoot());

            this.newsBinding = newsBinding;
            //title = (TextView)itemView.findViewById(R.id.titleval);
            //desc =(TextView)itemView.findViewById(R.id.descval);
            newsBinding.setRecyclerclick(new Presenters2() {
                @Override
                public void onclickListener() {

                    int pos = getAdapterPosition();
                    if(pos != RecyclerView.NO_POSITION) {
                        allusermodel clickedDataItem = list1.get(pos);

                        /*Intent intent = new Intent(context,messagelist.class);
                        intent.putExtra("clickid", clickedDataItem.getId());

                        context.startActivity(intent);*/
                    }

                }
            });
        }

        public void bind(allusermodel newsModel1)
        {
            this.newsBinding.setRecyclerclick((Presenters2) newsModel1);
        }

        public Entrys getNewsBinding()
        {
            return newsBinding;
        }

    }
}
