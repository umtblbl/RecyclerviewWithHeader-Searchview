package com.example.example3nd;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    LayoutInflater inflater;
    ArrayList<String> list;

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_LIST = 1;


    public RecyclerViewAdapter(Context context) {
        inflater = LayoutInflater.from(context);
        list = new ArrayList<String>();
        list.add("İstanbul");
        list.add("Ankara");
        list.add("Erzincan");
        list.add("İzmir");
        list.add("İzmir");
        list.add("Manisa");
        list.add("Karaman");
        list.add("Burdur");
        list.add("Isparta");
        list.add("Aksaray");
        list.add("Edirne");
        list.add("Kırklareli");
        list.add("Kırıkkale");
        list.add("Konya");
        list.add("Mersin");
        list.add("Antalya");
        list.add("Aydın");
        list.add("Muğla");
        list.add("Denizli");
        list.add("Iğdır");
        list.add("Muş");
        list.add("Van");
        list.add("Hatay");
        list.add("Gaziantep");
        list.add("ŞanlıUrfa");
        list.add("Amasya");
        list.add("Trabzon");
        list.add("Bolu");
        list.add("Artvin");
        list.add("Giresun");
        list.add("Sinop");
        list.add("Samsun");
        list.add("Bursa");


    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view;


        if (viewType == TYPE_HEADER) {
            view = inflater.inflate(R.layout.header, parent, false);
            return new ViewHolder(view, viewType);
        } else if (viewType == TYPE_LIST) {
            view = inflater.inflate(R.layout.item, parent, false);
            return new ViewHolder(view, viewType);
        } else {
            return null;
        }


    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        if (holder.view_type == TYPE_LIST) {

            holder.txtItem.setText(list.get(--position));
        } else if (holder.view_type == TYPE_HEADER) {

        }

    }

    @Override
    public int getItemCount() {
        return list.size() + 1;
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtItem;
        int view_type;

         ViewHolder(View itemView, int viewType) {
            super(itemView);

            if (viewType == TYPE_LIST) {
                txtItem = (TextView) itemView.findViewById(R.id.textView);
                view_type = 1;
            } else if (viewType == TYPE_HEADER) {
                view_type = 0;
            }


        }

    }


    @Override
    public int getItemViewType(int position) {

        if (position == 0)
            return TYPE_HEADER;
        return TYPE_LIST;
    }
}