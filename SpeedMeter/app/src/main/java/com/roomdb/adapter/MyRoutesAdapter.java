package com.roomdb.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.azspeedometer.R;
import com.roomdb.model.MyRoute;

import java.util.List;

public class MyRoutesAdapter extends RecyclerView.Adapter<MyRoutesAdapter.BeanHolder> {

    private List<MyRoute> list;
    private Context context;
    private LayoutInflater layoutInflater;
    private OnMyRouteItemClick onMyRouteItemClick;

    public MyRoutesAdapter(List<MyRoute> list, Context context) {
        layoutInflater = LayoutInflater.from(context);
        this.list = list;
        this.context = context;
        this.onMyRouteItemClick = (OnMyRouteItemClick) context;
    }


    @Override
    public BeanHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.note_list_item,parent,false);
        return new BeanHolder(view);
    }

    @Override
    public void onBindViewHolder(BeanHolder holder, int position) {
        Log.e("bind", "onBindViewHolder: "+ list.get(position));
        holder.textViewTitle.setText(list.get(position).getTitle());
        holder.textViewContent.setText(list.get(position).getRoute_data());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class BeanHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView textViewContent;
        TextView textViewTitle;
        public BeanHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            textViewContent = itemView.findViewById(R.id.item_text);
            textViewTitle = itemView.findViewById(R.id.tv_title);
        }

        @Override
        public void onClick(View view) {
            onMyRouteItemClick.onMyRouteClick(getAdapterPosition());
        }
    }

    public interface OnMyRouteItemClick{
        void onMyRouteClick(int pos);
    }
}