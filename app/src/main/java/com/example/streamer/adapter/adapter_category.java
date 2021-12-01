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
import com.example.streamer.Sub_Category;
import com.example.streamer.custom.category;
import com.example.streamer.custom.main_category;

import java.util.ArrayList;


public class adapter_category extends RecyclerView.Adapter<adapter_category.ViewHolder> {

    ArrayList<category> customMain ;
    private Context context;
    public static final String cattoSubcat = "cattoSubcat";


    // RecyclerView recyclerView;
    public adapter_category(Context context,  ArrayList<category> customMain)
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
    public void onBindViewHolder(ViewHolder holder, int position)
    {

        final String myListData = customMain.get(position).getCategory_name();
            holder.textView.setText(customMain.get(position).getCategory_name());

            //holder.imageView.setImageResource(listdata[position].getImgId());

            holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Toast.makeText(context, customMain.get(position).getId(), Toast.LENGTH_LONG).show();
                    Intent in = new Intent(context, Sub_Category.class);
                    in.putExtra(cattoSubcat,customMain.get(position).getId());
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