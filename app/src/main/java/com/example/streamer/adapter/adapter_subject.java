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
import com.example.streamer.Subject_TO_Question;
import com.example.streamer.custom.category;
import com.example.streamer.custom.sub_category;
import com.example.streamer.custom.subject;

import java.util.ArrayList;


public class adapter_subject extends RecyclerView.Adapter<adapter_subject.ViewHolder> {

    ArrayList<subject> customSubject ;
    private Context context;
    String category_id;
    String sub_category_id;
    public static final String SOQtoSTQ_cId = "SOQtoSTQ_cId";
    public static final String SOQtoSTQ_ScId = "SOQtoSTQ_ScId";
    public static final String SOQtoSTQ_sId = "SOQtoSTQ_sId";


    // RecyclerView recyclerView;
    public adapter_subject(Context context,  ArrayList<subject> customSubject, String category_id, String sub_category_id)
    {
        this.context = context;
        this.customSubject = customSubject;
        this.category_id = category_id;
        this.sub_category_id = sub_category_id;
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

        final String myListData = customSubject.get(position).getSubject();
        holder.textView.setText(customSubject.get(position).getSubject());


        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(context, customSubject.get(position).getId(), Toast.LENGTH_LONG).show();
                Intent in = new Intent(context, Subject_TO_Question.class);
                in.putExtra(SOQtoSTQ_sId,customSubject.get(position).getId());
                in.putExtra(SOQtoSTQ_cId,category_id);
                in.putExtra(SOQtoSTQ_ScId,sub_category_id);
                context.startActivity(in);

            }
        });

    }


    @Override
    public int getItemCount()
    {
        return customSubject.size();
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