package com.example.streamer.VideoStreaming;

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


public class mainmenuAdapter extends RecyclerView.Adapter<mainmenuAdapter.ViewHolder> {

    private String[ ] listdata;
    private Context context;
    String doc = "" , collection = "";
    public static final String collectionExport = "Success1";
    public static final String subExport = "Success2";


    // RecyclerView recyclerView;
    public mainmenuAdapter(Context context, String[ ] listdata, String collection) {
        this.context = context;
        this.listdata = listdata;
        this.collection = collection ;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.row_of_course_available, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final String myListData = listdata[position];
        holder.textView.setText(listdata[position]);
        //holder.imageView.setImageResource(listdata[position].getImgId());
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(),"click on item: "+myListData,Toast.LENGTH_LONG).show();
                Intent in = new Intent(context, videolist_incoming.class);
                in.putExtra(collectionExport, collection);
                in.putExtra(subExport, myListData);
                context.startActivity(in);

            }
        });
    }


    @Override
    public int getItemCount() {
        return listdata.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        //public ImageView imageView;
        public TextView textView;
        public RelativeLayout relativeLayout;
        public ViewHolder(View itemView) {
            super(itemView);
            //this.imageView = (ImageView) itemView.findViewById(R.id.imageView);
            this.textView = (TextView) itemView.findViewById(R.id.tv);
            relativeLayout = (RelativeLayout)itemView.findViewById(R.id.rl);
        }
    }
}
