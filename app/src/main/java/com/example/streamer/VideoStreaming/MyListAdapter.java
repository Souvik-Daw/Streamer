package com.example.streamer.VideoStreaming;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.streamer.R;


public class MyListAdapter extends RecyclerView.Adapter<MyListAdapter.ViewHolder> {


    private String[ ] listdata;
    private Context context;
    String doc = "" , collection = "";
    public static final String docExport = "Success3";
    public static final String collectionExport = "Success4";
    public static final String positionExport = "Success5";


    // RecyclerView recyclerView;
    public MyListAdapter(Context context, String[ ] listdata, String collection, String doc) {
        this.context = context;
        this.listdata = listdata;
        this.collection = collection ;
        this.doc = doc;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.customdesign, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final String myListData = listdata[position];
        holder.textView.setText(listdata[position]);

        //holder.imageView.setImageResource(listdata[position].getImgId());
        /*holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(),"click on item: "+myListData,Toast.LENGTH_LONG).show();
                Intent in = new Intent(context, MainActivity.class);
                in.putExtra(collectionExport, collection);
                in.putExtra(docExport, doc);
                in.putExtra(positionExport, position+"");
                context.startActivity(in);

            }
        });*/

        Shader textShader=new LinearGradient(0, 0, 100, 20,
                new int[]{Color.BLUE, Color.parseColor("#FF0000")},
                new float[]{0, 1}, Shader.TileMode.CLAMP);
        holder.tvWatch.getPaint().setShader(textShader);
        //holder.tvTest.getPaint().setShader(textShader);
        holder.llCollapsingLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(v.getContext(),"click on item: "+myListData,Toast.LENGTH_LONG).show();
                Intent in = new Intent(context, HomeVideo.class);
                in.putExtra(collectionExport, collection);
                in.putExtra(docExport, doc);
                in.putExtra(positionExport, position+"");
                context.startActivity(in);

            }
        });

        holder.btnCollapse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.collpased){
                    holder.collpased = false;
                    holder.llCollapsingLayout.setVisibility(View.VISIBLE);
                    holder.btnCollapse.setImageResource(R.drawable.ic_up_arrow);
                } else {
                    holder.collpased = true;
                    holder.llCollapsingLayout.setVisibility(View.GONE);
                    holder.btnCollapse.setImageResource(R.drawable.ic_down_arrow);
                }
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
        //public RelativeLayout relativeLayout;

        TextView tvWatch;
        //TextView tvTest;
        LinearLayout llTestExercise, llCollapsingLayout;
        ImageButton btnCollapse;
        boolean collpased = true;


        public ViewHolder(View itemView) {
            super(itemView);
            //this.imageView = (ImageView) itemView.findViewById(R.id.imageView);
            this.textView = (TextView) itemView.findViewById(R.id.tv);
            //relativeLayout = (RelativeLayout)itemView.findViewById(R.id.rl);

            tvWatch = itemView.findViewById(R.id.tv_watch);
            //tvTest = itemView.findViewById(R.id.tv_test);
            //llTestExercise = itemView.findViewById(R.id.ll_test_exercise);
            btnCollapse = itemView.findViewById(R.id.btn_collapse);
            llCollapsingLayout = itemView.findViewById(R.id.ll_collapsing_layout);
        }
    }
}
