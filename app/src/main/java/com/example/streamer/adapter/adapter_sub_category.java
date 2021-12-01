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
import com.example.streamer.Subject_OR_Question;
import com.example.streamer.custom.category;
import com.example.streamer.custom.sub_category;

import java.util.ArrayList;


public class adapter_sub_category extends RecyclerView.Adapter<adapter_sub_category.ViewHolder> {

    ArrayList<sub_category> customSubCategory ;
    private Context context;
    String category_id;
    public static final String subToSOQ_Cid = "subToSOQ_Cid";
    public static final String subToSOQ_SCid = "subToSOQ_SCid";


    // RecyclerView recyclerView;
    public adapter_sub_category(Context context,  ArrayList<sub_category> customSubCategory, String category_id)
    {
        this.context = context;
        this.customSubCategory = customSubCategory;
        this.category_id = category_id;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.custom_design, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position)
    {

        final String myListData = customSubCategory.get(position).getSub_category();
        holder.textView.setText(customSubCategory.get(position).getSub_category());

        //holder.imageView.setImageResource(listdata[position].getImgId());

        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(context, customSubCategory.get(position).getId(), Toast.LENGTH_LONG).show();
                Intent in = new Intent(context, Subject_OR_Question.class);
                in.putExtra(subToSOQ_SCid,customSubCategory.get(position).getId());
                in.putExtra(subToSOQ_Cid,category_id);
                context.startActivity(in);

            }
        });

    }


    @Override
    public int getItemCount()
    {
        return customSubCategory.size();
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