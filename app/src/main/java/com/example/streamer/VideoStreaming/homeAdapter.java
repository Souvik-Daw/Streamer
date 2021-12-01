package com.example.streamer.VideoStreaming;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.streamer.R;
import com.facebook.shimmer.Shimmer;
import com.facebook.shimmer.ShimmerDrawable;


public class homeAdapter extends RecyclerView.Adapter<homeAdapter.ViewHolder> {

    private String[ ] listdata;
    private Context context;
    String doc = "" , collection = "";
    public static final String docExport = "Success3";
    public static final String collectionExport = "Success4";
    public static final String positionExport = "Success5";

    // RecyclerView recyclerView;
    public homeAdapter(Context context, String[ ] listdata)
    {
        this.context = context;
        this.listdata = listdata;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.row_educator, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final String myListData = listdata[position];

        //holder.textView.setText(listdata[position]);
        //holder.imageView.setImageResource(listdata[position].getImgId());

        //Initialize shimmer
        Shimmer shimmer = new Shimmer.ColorHighlightBuilder()
                .setBaseColor(Color.parseColor("#F3F3F3"))
                .setBaseAlpha(1)
                .setHighlightColor(Color.parseColor("#E7E7E7"))
                .setHighlightAlpha(1)
                .setDropoff(50)
                .build();

        //Initialize shimmer drawable
        ShimmerDrawable shimmerDrawable = new ShimmerDrawable();
        //Set shimmer
        shimmerDrawable.setShimmer(shimmer);


        Glide.with(context)
                .load(listdata[position])
                .into(holder.imageView);

        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Toast.makeText(context,"edbvbevb",Toast.LENGTH_LONG).show();

            }
        });
    }


    @Override
    public int getItemCount() {
        return listdata.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        //public TextView textView;
        public RelativeLayout relativeLayout;
        public ViewHolder(View itemView) {
            super(itemView);
            this.imageView = (ImageView) itemView.findViewById(R.id.iv_avatar);
            //this.textView = (TextView) itemView.findViewById(R.id.tv);
            relativeLayout = (RelativeLayout)itemView.findViewById(R.id.rl_edu);
        }
    }
}