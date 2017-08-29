package com.example.shubhamekka.apis;

import android.support.design.widget.Snackbar;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.Intent;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import com.bumptech.glide.Glide;

import java.util.Arrays;
import java.util.List;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.PersonViewHolder> {
    public static class PersonViewHolder extends RecyclerView.ViewHolder {

        CardView cv;
        TextView description;
        TextView title;
        ImageView photo;

        PersonViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cv);
            title = (TextView)itemView.findViewById(R.id.title);
            description = (TextView)itemView.findViewById(R.id.description);
            photo = (ImageView)itemView.findViewById(R.id.photo);
        }
    }

    List<showing> display;

    RVAdapter(List<showing> display){
        this.display = display;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }


    @Override
    public PersonViewHolder onCreateViewHolder(final ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item, viewGroup, false);
        final PersonViewHolder pvh = new PersonViewHolder(v);
        v.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                int index=pvh.getAdapterPosition();
                Intent i=new Intent(viewGroup.getContext(),ArticleRead.class);
                int post_ids[]=new int[display.size()];
                String imageUrls[]=new String[display.size()];
                for (int j = 0; j < display.size(); j++) {
                    post_ids[j]=display.get(j).post_id;
                    imageUrls[j]=display.get(j).displayd_image;
                }
                i.putExtra("position",index);
                i.putExtra("post_ids",post_ids);
                i.putExtra("imageUrls",imageUrls);
                i.putExtra("size",display.size());
                viewGroup.getContext().startActivity(i);

            }
        });
        return pvh;
    }

    @Override
    public void onBindViewHolder(PersonViewHolder personViewHolder, int i) {
        personViewHolder.title.setText(display.get(i).post_title);
        personViewHolder.description.setText(display.get(i).post_excerpt);
        Glide.with(MainActivity.getContext())
                .load(display.get(i).displayd_image)
                .placeholder(R.drawable.ic_glide_waiting)
                .error(R.drawable.ic_glide_failed)
                .crossFade()
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(personViewHolder.photo);
    }


    @Override
    public int getItemCount() {
        return display.size();
    }
}
