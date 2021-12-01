package com.example.streamer.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.recyclerview.widget.RecyclerView;

import com.example.streamer.R;
import com.example.streamer.Category;
import com.example.streamer.custom.main_category;

import java.util.ArrayList;


public class adapter_main_category extends RecyclerView.Adapter<adapter_main_category.ViewHolder> {

    ArrayList<main_category> customMain ;
    private Context context;
    public static final String maincattoCat = "mainCategorytoCat";


    // RecyclerView recyclerView;
    public adapter_main_category(Context context,  ArrayList<main_category> customMain)
    {
        this.context = context;
        this.customMain = customMain;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.custom_design, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final String myListData = customMain.get(position).getMain_category();
        holder.textView.setText(customMain.get(position).getMain_category());

        //holder.imageView.setImageResource(listdata[position].getImgId());

        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Toast.makeText(context,customMain.get(position).getId(),Toast.LENGTH_LONG).show();
                Intent in = new Intent(context, Category.class);
                in.putExtra(maincattoCat,customMain.get(position).getId());
                context.startActivity(in);

            }
        });
    }


    @Override
    public int getItemCount() {
        return customMain.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        //public ImageView imageView;
        public TextView textView;
        public RelativeLayout relativeLayout;
        public ViewHolder(View itemView) {
            super(itemView);
            //this.imageView = (ImageView) itemView.findViewById(R.id.imageView);
            this.textView = (TextView) itemView.findViewById(R.id.maintext);
            relativeLayout = (RelativeLayout)itemView.findViewById(R.id.rl);
        }
    }
}